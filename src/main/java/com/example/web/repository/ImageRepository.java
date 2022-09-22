package com.example.web.repository;

import com.example.web.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
	Image findByLink(String link);

	@Query(nativeQuery = true, value = "SELECT link FROM images WHERE created_by = ?1")
	List<String> getListImageOfUser(long userId);
	
	@Query(value = "SELECT 1 FROM brand b WHERE b.thumbnail =?1",nativeQuery = true)
	Integer checkImageInUse(String link);
}
