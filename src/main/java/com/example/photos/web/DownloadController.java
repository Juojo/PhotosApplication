package com.example.photos.web;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.photos.model.Photo;
import com.example.photos.service.PhotosService;

@RestController
public class DownloadController {

	private final PhotosService photosService;
	
	public DownloadController(PhotosService photosService) {
		this.photosService = photosService;
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> download(@PathVariable String id) {
		Photo photo = photosService.get(id);
		if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		byte[] data = photo.getData();
		HttpHeaders headers = new HttpHeaders();
		ContentDisposition build = ContentDisposition
				.builder("inline")
				.filename(photo.getFileName())
				.build();
		
		headers.setContentType(MediaType.valueOf(photo.getContentType()));
		headers.setContentDisposition(build);
		
		return new ResponseEntity<>(data, headers, HttpStatus.OK);
	}
	
}
