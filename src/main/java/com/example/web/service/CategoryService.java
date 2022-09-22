package com.example.web.service;

import com.example.web.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
	List<Category> getListCategory();

	Page<Category> getAllCatrgory(int pageNo, int pageSize, String sortBy, String sortDir);
}
