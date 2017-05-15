/**
 * 
 */
package com.cf.code.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cf.code.entity.Music;
import com.cf.code.entity.enums.MusicCategory;
import com.cf.code.service.CloudService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

/**
 * @author congfeng
 *
 */
@Service("cloudCosService")
public class CloudCosServiceImpl implements CloudService{

	private static Logger log = LogManager.getLogger(CloudCosServiceImpl.class);
	
	private static final String MusicServerPath = "querymusic";
	
	@Value("#{sys.CosRegion}")
	String CosRegion;
	
	@Value("#{sys.CosAppId}")
	Long CosAppId;
	
	@Value("#{sys.CosSecretId}")
	String CosSecretId;
	
	@Value("#{sys.CosSecretKey}")
	String CosSecretKey;
	
	@Value("#{sys.CosBucket4Music}")
	String CosBucket4Music;
	
	@Value("#{sys.CosBucket4Server}")
	String CosBucket4Server;
	
	private COSClient cosClient;
	
	@PostConstruct
	private void init(){
		Credentials cred = new Credentials(CosAppId, CosSecretId, CosSecretKey);
		ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(CosRegion);
        this.cosClient = new COSClient(clientConfig, cred);
	}

	@PreDestroy
	private void destroy(){
		if(this.cosClient != null){
			this.cosClient.shutdown();
		}
	}

	@Override
	public void uploadMusicList(MusicCategory mc,List<Music> musics,Integer pageNo) {
		String cosPath = "/" + MusicServerPath + "/" + mc.value + "_" + pageNo;
		byte[] content = JSONObject.toJSONBytes(musics);
		UploadFileRequest request = new UploadFileRequest(CosBucket4Server, cosPath, content);
		this.cosClient.uploadFile(request);
	}

	@Override
	public void uploadMusic(Byte category,String fileName,byte[] data) {
		String cosPath = category+"/"+fileName;
		UploadFileRequest request = new UploadFileRequest(CosBucket4Music, cosPath, data);
		this.cosClient.uploadFile(request);
	}
	
}
