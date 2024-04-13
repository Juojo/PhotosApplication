package com.example.photos;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotosController {

	private List<Photo> db = List.of(new Photo("1", "photo.png"), new Photo("2", "hola.png"));
	
	@GetMapping("/")
	public String hello() {
		return "Hello world!";
	}
	
	@GetMapping("/photos")
	public List<Photo> get() {
		return db;
	}
}

// 18:40 -> https://www.youtube.com/watch?v=QuvS_VLbGko