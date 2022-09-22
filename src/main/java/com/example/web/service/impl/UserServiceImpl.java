package com.example.web.service.impl;

import com.example.web.entity.User;
import com.example.web.repository.UserRepository;
import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Override
		public Page<User> getAllUser(int pageNo, int pageSize, String sortBy, String sortDir) {
			Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
					: Sort.by(sortBy).descending();
			Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
			return userRepository.findAll(pageable);
	}
}
