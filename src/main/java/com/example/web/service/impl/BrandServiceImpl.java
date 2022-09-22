package com.example.web.service.impl;

import com.example.web.entity.Brand;
import com.example.web.repository.BrandRepository;
import com.example.web.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class BrandServiceImpl implements BrandService {
	
	@Autowired
	BrandRepository brandRepository;
	
	@Override
	public Page<Brand> getAllBrand(int pageNo, int pageSize, String sortBy, String sortDir) {
	Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
			: Sort.by(sortBy).descending();
	Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
	return brandRepository.findAll(pageable);
	}

	@Override
	public List<Brand> getListBrand() {
		return brandRepository.findAll();
	}
	
	
}
