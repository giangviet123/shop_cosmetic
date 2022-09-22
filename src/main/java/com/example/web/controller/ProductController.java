package com.example.web.controller;

import com.example.web.entity.Brand;
import com.example.web.entity.Category;
import com.example.web.entity.Product;
import com.example.web.entity.User;
import com.example.web.repository.ProductRepository;
import com.example.web.service.BrandService;
import com.example.web.service.CategoryService;
import com.example.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("admin/product")
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BrandService brandService;

	@GetMapping("/admin/products/create")
	public String getProductCreatePage(Model model) {
		//Lấy danh sách anh của user
//		User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
//		List<String> images = imageService.getListImageOfUser(user.getId());
//		model.addAttribute("images", images);
		
		//Lấy danh sách nhãn hiệu
		List<Brand> brands = brandService.getListBrand();
		model.addAttribute("brands", brands);
		//Lấy danh sách danh mục
		List<Category> categories = categoryService.getListCategory();
		model.addAttribute("categories", categories);
		
		return "admin/product/create";
	}
	@GetMapping("/admin/products/{slug}/{id}")
	public String getProductUpdatePage(Model model, @PathVariable Long id) {
		
		// Lấy thông tin sản phẩm theo id
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		
		// Lấy danh sách ảnh của user
		//List<String> images = imageService.getListImageOfUser(user.getId());
		//model.addAttribute("images", images);
		
		// Lấy danh sách danh mục
//		List<Category> categories = categoryService.getListCategories();
//		model.addAttribute("categories", categories);
		
		// Lấy danh sách nhãn hiệu
		List<Brand> brands = brandService.getListBrand();
		model.addAttribute("brands", brands);
		
		
		return "admin/product/edit";
	}
	@PostMapping("insertProduct")
	//method overloading
	public String insertProduct(ModelMap modelMap,@Valid @ModelAttribute("product") Product product,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return "insertProduct";
		}
		try {
			productRepository.save(product);
			return "redirect:/categories";
		}catch (Exception e) {
			modelMap.addAttribute("error", e.toString());
			return "insertProduct";
		}
	}
//
//	@RequestMapping(value = "/deleteProduct/{productID}", method = RequestMethod.POST)
//	public String deleteProduct(ModelMap modelMap, @PathVariable String productID) {
//		productRepository.deleteById(productID);
//		return "redirect:/categories";
//	}
//	///products/updateProduct/${product.getProductID()}
//	@RequestMapping(value = "/updateProduct/{productID}", method = RequestMethod.POST)
//	public String updateProduct(ModelMap modelMap,
//								@Valid @ModelAttribute("product") Product product,
//								BindingResult bindingResult,
//								@PathVariable String productID
//	) {
//		Iterable<Category> categories = categoryRepository.findAll();
//		if(bindingResult.hasErrors()) {
//			modelMap.addAttribute("categories", categories);
//			return "updateProduct";//updateProduct.jsp
//		}
//		try {
//			if(productRepository.findById(productID).isPresent()) {
//				Product foundProduct = productRepository
//						.findById(product.getProductID()).get();
//				if(!product.getProductName().trim().isEmpty()) {
//					foundProduct.setProductName(product.getProductName() );
//				}
//				if(!product.getCategoryID().isEmpty()) {
//					foundProduct.setCategoryID(product.getCategoryID());
//				}
//				//is empty => "" OR NULL
//				if(!product.getDescription().trim().isEmpty()) {
//					foundProduct.setDescription(product.getDescription());
//				}
//				if(product.getPrice() > 0) {
//					foundProduct.setPrice(product.getPrice());
//				}
//				productRepository.save(foundProduct);
//			}
//		}catch (Exception e) {
//			return "updateProduct";//updateProduct.jsp
//		}
//		return "redirect:/products/getProductsByCategoryID/"+product.getCategoryID();
//	}

}
