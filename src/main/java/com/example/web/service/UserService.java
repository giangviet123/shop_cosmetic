package com.example.web.service;

import com.example.web.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
	Page<User> getAllUser(int pageNo, int pageSize, String sortBy, String sortDir);
}
