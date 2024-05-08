package com.example.photos.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.photos.model.Photo;

@Service
public class PhotosService {

	private Map<String, Photo> db = new HashMap<>() {{
		put("1", new Photo("1", "photo", ".png", null));
	}};

	
	public Collection<Photo> get() {
		return db.values();
	}
	
	public Photo get(String id) {
		return db.get(id);
	}
	
	public Photo remove(String id) {
		return db.remove(id);
	}
	
	public Photo save(String fileName, String contentType, byte[] data) {
		Photo photo = new Photo(UUID.randomUUID().toString(), fileName, contentType, data);
		db.put(photo.getId(), photo);
		return photo;
	}}
