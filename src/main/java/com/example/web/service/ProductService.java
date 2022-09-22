package com.example.web.service;

import com.example.web.entity.Product;

import java.util.Optional;

public interface ProductService {
	Product getProductById(Long id);
}
