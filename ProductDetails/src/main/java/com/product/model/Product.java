package com.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Product")
@Data
public class Product {

	@Transient
	public static final String SEQUENCE_NAME = "products_sequence";

	@Id
	private int id;
	private String productName;
	private String category;
	private String image;
	private Double price;
	private int rating;
	private String description;
	private int countInStock;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", category=" + category + ", image=" + image
				+ ", price=" + price + ", rating=" + rating + ", description=" + description + ", countInStock="
				+ countInStock + "]";
	}

	public Product(int id, String productName, String category, String image, Double price, int rating,
			String description, int countInStock) {
		super();
		this.id = id;
		this.productName = productName;
		this.category = category;
		this.image = image;
		this.price = price;
		this.rating = rating;
		this.description = description;
		this.countInStock = countInStock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCountInStock() {
		return countInStock;
	}

	public void setCountInStock(int countInStock) {
		this.countInStock = countInStock;
	}

}
