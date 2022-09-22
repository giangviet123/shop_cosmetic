package com.example.web.service;

import com.example.web.entity.Image;

import java.util.List;
public interface ImageService {
	
	void saveImage(Image image);
	void deleteImage(String uploadDir,String filename);
	List<String> getListImageOfUser(long userId);
}
