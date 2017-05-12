/**
 * 
 */
package com.cf.code.service.impl;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.cf.code.entity.Music;
import com.cf.code.entity.enums.MusicCategory;
import com.cf.code.service.CloudService;

/**
 * @author congfeng
 *
 */
@Service("cloudOssService")
public class CloudOssServiceImpl implements CloudService{

	private static Logger log = LogManager.getLogger(CloudOssServiceImpl.class);
	
	private static final String MusicListPrefix = "musiclist_";
	
	@Value("#{sys.Endpoint}")
	String Endpoint;
	
	@Value("#{sys.AccessKeyId}")
	String AccessKeyId;
	
	@Value("#{sys.AccessKeySecret}")
	String AccessKeySecret;
	
	@Value("#{sys.Bucket4Book}")
	String Bucket4Book;
	
	@Value("#{sys.Bucket4Music}")
	String Bucket4Music;
	
	@Value("#{sys.Bucket4Image}")
	String Bucket4Image;
	
	private OSSClient ossClient;
	
	@PostConstruct
	private void init(){
		ClientConfiguration conf = new ClientConfiguration();
		// 设置OSSClient使用的最大连接数，默认1024
		conf.setMaxConnections(200);
		// 设置请求超时时间，默认50秒
		conf.setSocketTimeout(10000);
		// 设置失败请求重试次数，默认3次
		conf.setMaxErrorRetry(5);
		this.ossClient = new OSSClient(Endpoint, AccessKeyId, AccessKeySecret, conf);
	}

	@PreDestroy
	private void destroy(){
		if(this.ossClient != null){
			this.ossClient.shutdown();
		}
	}

	@Override
	public void uploadMusicList(MusicCategory mc,List<Music> musics,Integer pageNo) {
		String key = mc.value+"/"+MusicListPrefix+pageNo;
		byte[] content = JSONObject.toJSONBytes(musics);
		this.ossClient.putObject(Bucket4Music,key, new ByteArrayInputStream(content));
	}

	@Override
	public void uploadMusic(Byte category,String fileName,byte[] data) {
		String key = category+"/"+fileName;
		this.ossClient.putObject(Bucket4Music,key, new ByteArrayInputStream(data));
	}
	
}
