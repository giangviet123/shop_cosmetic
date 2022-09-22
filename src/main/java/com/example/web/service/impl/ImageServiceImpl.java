package com.example.web.service.impl;

import com.example.web.entity.Image;
import com.example.web.exception.BadRequestException;
import com.example.web.exception.InternalServerException;
import com.example.web.repository.ImageRepository;
import com.example.web.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.List;

public class ImageServiceImpl implements ImageService {
	
	@Autowired
	ImageRepository imageRepository;


	@Override
	public void saveImage(Image image) {
		imageRepository.save(image);
	}
	
	@Override
	public void deleteImage(String uploadDir, String filename) {
		//Lấy đường dẫn file
		String link = "/media/static/" + filename;
		//Kiểm tra link
		Image image = imageRepository.findByLink(link);
		if (image == null) {
			throw new BadRequestException("File không tồn tại");
		}
		
		//Kiểm tra ảnh đã được dùng
		Integer inUse = imageRepository.checkImageInUse(link);
		if (inUse != null) {
			throw new BadRequestException("Ảnh đã được sử dụng không thể xóa!");
		}
		
		//Xóa file trong databases
		imageRepository.delete(image);
		
		//Kiểm tra file có tồn tại trong thư mục
		File file = new File(uploadDir + "/" + filename);
		if (file.exists()) {
			//Xóa file ở thư mục
			if (!file.delete()) {
				throw new InternalServerException("Lỗi khi xóa ảnh!");
			}
		}
	}
	
	@Override
	public List<String> getListImageOfUser(long userId) {
		return null;
	}
}
