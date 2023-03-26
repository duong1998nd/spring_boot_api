package com.locShop.controller.admin;


import com.locShop.model.CategoryEntity;
import com.locShop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.locShop.model.ProductEntity;
import com.locShop.service.CategoryService;
import com.locShop.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/admin/product")
public class adminProduct {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StorageService service;

	@RequestMapping("")
	public List<ProductEntity> findAll() {
		List<ProductEntity> products = productService.findAll();
		return products;
	}

	@GetMapping(value = "/details")
	public Optional<ProductEntity> findById(@RequestParam("id") Long id) {
		return productService.findById(id);
	}

	@PostMapping(value = "")
	public String insertProduct(@RequestParam("image") MultipartFile file,
								@RequestParam("name") String name,
								@RequestParam("price") Float price,
								@RequestParam("sale_price") Float sale_price,
								@RequestParam("desciption") String desc,
								@RequestParam("author") String author,
								@RequestParam("category_id") Long category_id) throws IOException {

		ProductEntity pro = new ProductEntity(name,file.getOriginalFilename(),price,sale_price,author,desc,categoryService.findById(category_id).orElse(null));
		String uploadImage = service.uploadImage(file);
		if(uploadImage!=null) {
			if(productService.save(pro)!=null){
				return "thêm mới thành công";
			}
			return "thêm mới thất bại";
		}
		return "thêm mới thất bại";
	}

	@PutMapping("")
	public String updateProduct(@Valid @RequestBody ProductEntity pro){
		if(productService.save(pro) != null) {
			return "update thành công";
		}
		return "update thất bại";
	}

	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable Long id){

		ProductEntity proById = productService.findById(id).orElse(null);
		System.out.println(proById.getId());
		if(proById!=null){
			productService.deleteById(id);
			return "đã xóa sản phẩm có id="+id;
		}
		return "Không tìm thấy sản phẩm có id="+id;
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