package com.example.web.service;

import com.example.web.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {
	Page<Brand> getAllBrand(int pageNo, int pageSize, String sortBy, String sortDir);
	
	List<Brand> getListBrand();
}
