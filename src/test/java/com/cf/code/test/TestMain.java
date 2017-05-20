/**
 * 
 */
package com.cf.code.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import com.cf.code.common.FileUtil;

/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
public class TestMain {

	protected static Logger log = LogManager.getLogger(TestMain.class);
	
	static int i = 100;
	
	public static int getMp3TrackLength(File mp3File) {  
	    try {  
	        MP3File f = (MP3File)AudioFileIO.read(mp3File);  
	        MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();  
	        return audioHeader.getTrackLength();      
	    } catch(Exception e) {  
	        return -1;  
	    }  
	}  
	
	public static void main(String[] args) throws IOException {
		String dir = "/Users/congfeng/Documents/lx资料/赞偈";
		for(File file:FileUtils.listFiles(new File(dir), null, true)){
			int len = getMp3TrackLength(file);
			System.out.println(file.getName()+"   "+len/60+":"+len%60);
			
//			System.out.println(file.getName()+"-----"+file.length()+"------"+FileUtil.getDataSize(file.length()));
		}
		System.out.println(1312/60+":"+1312%60);
//	    List<String> ls = FileUtils.readLines(new File("/Users/congfeng/git/lx/saved_resource.html"));
//	    for(int i=0;i<ls.size();i++){
//	    	String info = ls.get(i).trim();
//	    	if(!info.startsWith("<p><a href=\"/plugin")){
//	    		continue;
//	    	}
//	    	String infod = info.toLowerCase();
//	    	if(infod.indexOf("bluetooth") > -1
//	    			||infod.indexOf("facebook") > -1
//	    			||infod.indexOf("admob") > -1
//	    			||infod.indexOf("paypal") > -1
//	    			||infod.indexOf("google") > -1){
//	    		continue;
//	    	}
//	    	String name = StringUtils.substringBetween(info, "\">", "</a>");
//    		String url = StringUtils.substringBetween(info, "href=\"", "\">");
//    		log.info((i+1)+"."+name+"：http://www.plugreg.com"+url);
//	    }
//	    System.out.println(ls.size());
	}
	
	
}
