package com.locShop.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.locShop.model.CategoryEntity;
import com.locShop.service.CategoryService;

import jakarta.validation.Valid;

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