/**
 * 
 */
package com.cf.code.entity;

/**
 * @author congfeng
 *
 */
public class MusicCollect {

	private Integer id;
	
	private Integer musicId;
	
	private String name;
	
	private String author;
	
	private String filesize;
	
	private String soundsize;
	
	private Byte category;
	
	private Integer userId;
	
	private String filename;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMusicId() {
		return musicId;
	}

	public void setMusicId(Integer musicId) {
		this.musicId = musicId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getSoundsize() {
		return soundsize;
	}

	public void setSoundsize(String soundsize) {
		this.soundsize = soundsize;
	}

	public Byte getCategory() {
		return category;
	}

	public void setCategory(Byte category) {
		this.category = category;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public boolean equals(Object obj) {
		MusicCollect that = (MusicCollect)obj;
		if(super.equals(that)){
			return true;
		}
		return this.userId.equals(that.userId)&&this.musicId.equals(that.musicId);
	}
	
}
