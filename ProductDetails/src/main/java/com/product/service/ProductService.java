package com.product.service;

import java.util.List;
import java.util.Optional;


import com.product.model.Product;

public interface ProductService {
	
	/**
	 * Persist save products.
	 * 
	 * @param product
	 *			- product
	 */
	public Product saveProduct(Product product);
	
	/**
	 * Find all products
	 * 
	 * @return all products
	 */
	public List<Product> getAllProduct();
	
	/**
	 * Find all product by Id
	 * 
	 * @param id
	 * 			- id
	 * @return Product By Id
	 */
	public Optional<Product> getProductById( int id);
	
	/**
	 * delete the product by id
	 * 
	 * @param id
	 * 			- id 
	 */
	public String deleteProduct( int id);
	
	/**
	 * Updating the product
	 * 
	 * @param product
	 * 			-product
	 * 
	 * @param id
	 * 			- id
	 * 
	 */
	public String updateProduct( Product product,  int id);
	
	/**
	 * Find products by name.
	 * 
	 * @param productName
	 * 			-productName
	 * 
	 * @return products by name
	 */
	public List<Product> getProductByProductName(String productName);
	
	/**
	 * Find products by Category.
	 * 
	 * @param productCategory
	 * 			-productCategory
	 * 
	 * @return products by Category
	 */
	public List<Product>getProductByCategory(String productCategory);
	
	boolean isProductExist(int id);

}
