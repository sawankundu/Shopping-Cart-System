package com.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.product.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
	
	/**
	 * Find all products by Category
	 * 
	 * @param productType
	 * 
	 * @return all products by Category
	 */
	List<Product> findAllByCategory(String productType);
	
	/**
	 * Find all products by Name
	 * 
	 * @param productName
	 * 
	 * @return all products by Name
	 */
	List<Product> findAllByProductName(String productName);

}
