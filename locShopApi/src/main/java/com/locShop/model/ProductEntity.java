package com.locShop.model;

import org.hibernate.annotations.ColumnDefault;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Tên sách không được để trống")
	@Size(min = 5,max = 250,message = "Tên phải ít nhất 5 ký tự max 250 ký tự")
	@Column(name = "name",nullable = false, unique = true)
	private String name;
	
	@NotBlank(message = "Ảnh của sách không được để trống")
	@Column(name = "image")
	private String image;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "sale_price")
	@ColumnDefault("0")
	private float sale_price;
	
	@NotBlank(message = "Tác giả không được để trống")
	@Column(name = "author")
	private String author;
	
	@Column(columnDefinition = "TEXT")
	private String desciption;
	
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private CategoryEntity category;
	
	public ProductEntity() {
	}
	
	
	public ProductEntity(
			@NotBlank(message = "Tên sách không được để trống") @Size(min = 5, max = 250, message = "Tên phải ít nhất 5 ký tự max 250 ký tự") String name,
			@NotBlank(message = "Ảnh của sách không được để trống") String image, float price, float sale_price,
			@NotBlank(message = "Tác giả không được để trống") String author, String desciption,
			CategoryEntity category_id) {
		this.name = name;
		this.image = image;
		this.price = price;
		this.sale_price = sale_price;
		this.author = author;
		this.desciption = desciption;
		this.category = category_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getSale_price() {
		return sale_price;
	}

	public void setSale_price(float sale_price) {
		this.sale_price = sale_price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Long getCategory_id() {
		return category.getId();
	}

	public void setCategory_id(CategoryEntity category_id) {
		this.category = category_id;
	}

	
}
