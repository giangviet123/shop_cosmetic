package com.example.web.controller;

import com.example.web.Constants;
import com.example.web.entity.User;
import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/user")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("list")
	public String listUser(@RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false) String sortBy,
						   @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
						   @RequestParam(value = "current", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) int pageNo ,
						   @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) int pageSize, Model model) {
		Page<User> list = userService.getAllUser(pageNo,pageSize,sortBy,sortDir);
		model.addAttribute("list",list);
		int totalPages = list.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "fragments/Admin/usertbody::usertbody";
	}

	@PutMapping("/{user_id}")
	public Object updateUser(@PathVariable("user_id") Integer id ,
							 BindingResult error) {
		if (error.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
//		boolean result = userService.update(id, user);
//		if (!result){
//			return new ResponseEntity<>("Cập nhật thông tin nhân viên không thành công",HttpStatus.BAD_REQUEST);
//		}
//		return new ResponseEntity<>("Cập nhật thông tin nhân viên thành công",HttpStatus.OK);
		return null;
	}
	
//	@DeleteMapping("/{user_id}")
//	public ResponseEntity<UserDto> deleteUser(@PathVariable("user_id") Integer id) {
//		userService.delete(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
}
