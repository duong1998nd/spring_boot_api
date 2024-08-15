package com.bShop.controller.admin;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bShop.model.ProductEntity;
import com.bShop.service.CategoryService;
import com.bShop.service.ProductService;
import com.bShop.service.StorageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "http://192.168.1.80:8098", maxAge = 3600)
@RestController
@RequestMapping("api/admin/product")
public class adminProduct {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StorageService service;
	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@RequestMapping("")
	public List<ProductEntity> findAll() {
		List<ProductEntity> products = productService.findAll();
		return products;
	}

	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@GetMapping(value = "/details")
	public Optional<ProductEntity> findById(@RequestParam("id") Long id) {
		return productService.findById(id);
	}

	@Operation(
			security = {@SecurityRequirement(name = "BearerJWT")},
			parameters = {@Parameter(name = "category_id",
			schema = @Schema(type = "string",
			allowableValues = {"1"},
			defaultValue = "1")
			)}
	)
	@PostMapping(value = "",consumes = {
			"multipart/form-data"
	})
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

	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@PutMapping(value = "",consumes = {
			"multipart/form-data"
	})
	public String updateProduct(@RequestParam("image") MultipartFile file,
								@RequestParam("id") Long id,
								@RequestParam("name") String name,
								@RequestParam("price") Float price,
								@RequestParam("sale_price") Float sale_price,
								@RequestParam("desciption") String desc,
								@RequestParam("author") String author,
								@RequestParam("category_id") Long category_id) throws IOException {
		ProductEntity pro = new ProductEntity(id,name,file.getOriginalFilename(),price,sale_price,author,desc,categoryService.findById(category_id).orElse(null));
		String uploadImage = service.uploadImage(file);
		if(uploadImage != null) {
			if(productService.save(pro)!=null){
				return "update thành công";
			}
			return "update thất bại";
		}
		return "update thất bại";
	}

	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@DeleteMapping("")
	public String deleteProduct(@RequestParam("id") Long id){
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