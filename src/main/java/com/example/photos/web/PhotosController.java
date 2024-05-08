package com.example.photos.web;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.photos.model.Photo;
import com.example.photos.service.PhotosService;

@RestController
public class PhotosController {

	private final PhotosService photosService;
	
	public PhotosController(PhotosService photosService) {
		this.photosService = photosService;
	}
	
	@GetMapping("/")
	public String hello() {
		return "Hello world!";
	}
	
	@GetMapping("/photos")
	public Collection<Photo> get() {
		return photosService.get();
	}
	
	@GetMapping("/photos/{id}")
	public Photo get(@PathVariable String id) {
		Photo result = photosService.get(id);
		if (result == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return result;
	}
	
	@DeleteMapping("/photos/{id}")
	public void delete(@PathVariable String id) {
		Photo result = photosService.remove(id);
		if (result == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/photos")
	public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
		return photosService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes()); 
	}
	
//	@PostMapping("/photos")
//	public Photo create(@RequestBody @Valid Photo newPhoto) {
//		newPhoto.setId(UUID.randomUUID().toString());
//		db.put(newPhoto.getId(), newPhoto);
//		return newPhoto;
//	}
}