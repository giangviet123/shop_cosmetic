package com.example.web.controller;

import com.example.web.Constants;
import com.example.web.entity.Category;
import com.example.web.repository.CategoryRepository;
import com.example.web.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
@RequestMapping("admin/category")
public class CategoryController {
		@Autowired
		CategoryRepository categoryRepository;
		@Autowired
		CategoryService categoryService;

		@GetMapping("page")
		public String listUser(@RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false) String sortBy,
							   @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
							   @RequestParam(value = "current", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) int pageNo ,
							   @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) int pageSize, Model model) {
			Page<Category> list = categoryService.getAllCatrgory(pageNo,pageSize,sortBy,sortDir);
			model.addAttribute("list",list);
			int totalPages = list.getTotalPages();
			if (totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
						.boxed()
						.collect(Collectors.toList());
				model.addAttribute("pageNumbers", pageNumbers);
			}
			return "";
		}
		
		
		
		@GetMapping(value = "/insert")
		public String insertProduct(ModelMap modelMap) {
			modelMap.addAttribute("categories", new Category());
			return "insertProduct";
		}
		
		@PostMapping(value = "/insert")
		//method overloading
		public String insertProduct(ModelMap modelMap, @Valid @ModelAttribute("categories") Category category,
									BindingResult bindingResult //validation
		) {
			if(bindingResult.hasErrors()) {
				return "insertProduct";
			}
			try {
				categoryRepository.save(category);
				return "redirect:/admin/category";
			}catch (Exception e) {
				modelMap.addAttribute("message","category is save!");
				return "insertProduct";
			}
		}

		@PostMapping("/delete/{category_id}")
		public String deleteCategory(ModelMap modelMap, @PathVariable Long category_id) {
			categoryRepository.deleteById(category_id);
			modelMap.addAttribute("message","category is delete!");
			return "redirect:/admin/category/page";
		}
		
		
		@RequestMapping(value = "/update/{category_id}", method = RequestMethod.POST)
		public String updateCategory(ModelMap modelMap,@Valid @ModelAttribute("categories") Category category,
									//BindingResult bindingResult,
									@PathVariable Long category_id
		) {
			
			try {
				if(categoryRepository.findById(category_id).isPresent()) {
					Category foundCategory = categoryRepository
							.findById(category.getId()).get();
					//is empty => "" OR NULL
					if(!category.getName().trim().isEmpty()) {
						foundCategory.setName(category.getName());
					}
					modelMap.addAttribute("message","Category is update!");
					categoryRepository.save(foundCategory);
				}
			}catch (Exception e) {
				return "updateCategory";//updateProduct.jsp
			}
			return "redirect:admin/category/page";
		}
		
		
}
