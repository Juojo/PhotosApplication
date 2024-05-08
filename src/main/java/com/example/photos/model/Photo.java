package com.example.photos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;

public class Photo {

	private String id;
	@NotEmpty
	private String fileName;
	
	private String contentType;
	
	@JsonIgnore
	private byte[] data;
	
	public Photo() {
		
	}
	
	public Photo(String id, String fileName, String contentType, byte[] data) {
		this.id = id;
		this.fileName = fileName;
		this.contentType = contentType;
		this.data = data;
	}
	
	public byte[] getData() {
		return data;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}
	
	
	
}
