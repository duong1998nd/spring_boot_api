package com.bShop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bShop.model.CategoryEntity;
import com.bShop.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://192.168.1.80:8098", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/category")
public class adminCategory {

	@Autowired
	private CategoryService categoryService;

	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@GetMapping("")
	public Iterable<CategoryEntity> listCategory(){
		return categoryService.findAll();
	}

	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@PostMapping("")
	public String insertCategory(@Valid @RequestBody CategoryEntity cat) {
		if(categoryService.save(cat) != null) {
			return "thêm mới thành công";
		}
		return "thêm mới thất bại";
	}
	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@PutMapping("")
	public String updateCategory(@Valid @RequestBody CategoryEntity cat) {
		if(categoryService.save(cat) != null) {
			return "update thành công";
		}
		return "update thất bại";
	}

	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable Long id){

		CategoryEntity catById = categoryService.findById(id).orElse(null);
		System.out.println(catById.getId());
		if(catById!=null){
			categoryService.deleteById(id);
			return "đã xóa danh mục có id="+id;
		}
		return "Không tìm thấy danh mục có id="+id;
	}
}