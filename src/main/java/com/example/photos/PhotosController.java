package com.example.photos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
public class PhotosController {

	private Map<String, Photo> db = new HashMap<>() {{
		put("1", new Photo("1", "photo.png"));
	}};
	
	//private List<Photo> db = List.of(new Photo("1", "photo.png"), new Photo("2", "hola.png"));
	
	@GetMapping("/")
	public String hello() {
		return "Hello world!";
	}
	
	@GetMapping("/photos")
	public Collection<Photo> get() {
		return db.values();
	}
	
	@GetMapping("/photos/{id}")
	public Photo get(@PathVariable String id) {
		Photo result = db.get(id);
		if (result == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return result;
	}
	
	@DeleteMapping("/photos/{id}")
	public void delete(@PathVariable String id) {
		Photo result = db.remove(id);
		if (result == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/photos")
	public Photo create(@RequestBody @Valid Photo newPhoto) {
		newPhoto.setId(UUID.randomUUID().toString());
		db.put(newPhoto.getId(), newPhoto);
		return newPhoto;
	}
}

// 18:40 -> https://www.youtube.com/watch?v=QuvS_VLbGko