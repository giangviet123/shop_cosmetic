package com.example.web.service.impl;

import com.example.web.entity.Category;
import com.example.web.repository.CategoryRepository;
import com.example.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	@Override
	public List<Category> getListCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public Page<Category> getAllCatrgory(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
		return categoryRepository.findAll(pageable);
	}
}
