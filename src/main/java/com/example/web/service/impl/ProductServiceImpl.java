package com.example.web.service.impl;

import com.example.web.entity.Product;
import com.example.web.repository.ProductRepository;
import com.example.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	
	
	@Override
	public Product getProductById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			return null;
		}
		return product.get();
	}
}
