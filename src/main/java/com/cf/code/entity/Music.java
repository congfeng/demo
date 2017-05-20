/**
 * 
 */
package com.cf.code.entity;

/**
 * @author congfeng
 *
 */
public class Music {

	private Integer id;
	
	private String name;
	
	private String author;
	
	private String filename;
	
	private String filesize;
	
	private String soundsize;
	
	private Byte category;
	
	private Integer collects;
	
	private Integer plays;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getCollects() {
		return collects;
	}

	public void setCollects(Integer collects) {
		this.collects = collects;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getPlays() {
		return plays;
	}

	public void setPlays(Integer plays) {
		this.plays = plays;
	}
	
}
