package com.locShop.controller.web;


import com.locShop.model.ProductEntity;
import com.locShop.service.CategoryService;
import com.locShop.service.ProductService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/product")
public class Product {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public List<ProductEntity> findAll() {
		List<ProductEntity> products = productService.findAll();
		return products;
	}

	@GetMapping(value = "/{id}")
	public Optional<ProductEntity> findById( @PathVariable Long id) {
		return productService.findById(id);
	}

	@RequestMapping("/categoryId={id}")
	public List<ProductEntity> findByCat(@PathVariable("id") Long id){
		List<ProductEntity> pros = productService.findAllByCategoryId(id);
		return pros;
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<String> errors = bindingResult.getAllErrors().stream()
				.map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList());
		return ResponseEntity.badRequest().body(String.join(", ", errors));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
}