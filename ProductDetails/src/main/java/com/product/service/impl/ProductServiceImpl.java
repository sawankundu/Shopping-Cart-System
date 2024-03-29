package com.product.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;

import com.product.model.Product;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public static final String EMPTY = "";

	@Override
	public Product saveProduct(Product product) {
		Product e = productRepository.save(product);
		return e;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> productList = productRepository.findAll();
		return productList;
	}

	@Override
	public Optional<Product> getProductById(int id) {
		// Optional<Product> findById= productRepository.findById(id);
		return productRepository.findById(id);
	}

	@Override
	public String deleteProduct(int id) {
		boolean isProductExist = productRepository.existsById(id);
		if (isProductExist) {
			productRepository.deleteById(id);
			return "Delete Product with id: " + id;
		} else {
			return "Can not delete as product not found with this ID";
		}
	}

	@Override
	public String updateProduct(Product product, int id) {
		boolean isProductExist = productRepository.existsById(id);
		if (isProductExist) {
			productRepository.save(product);
			return "Update cropdetails with id: " + id;
		} else {
			return "Can not update as product not found with this ID";
		}
	}

	@Override
	public List<Product> getProductByProductName(String productName) {
		List<Product> Name = productRepository.findAllByProductName(productName);
		return Name;
	}

	@Override
	public List<Product> getProductByCategory(String productCategory) {
		List<Product> Type = productRepository.findAllByCategory(productCategory);
		return Type;

	}

	@Override
	public boolean isProductExist(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Product> filterProducts(String category, double minPrice, double maxPrice) {
		List<Product> products = productRepository.findAll();
		List<Product> filteredProducts = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(products)) {
			filteredProducts = Stream.ofNullable(products).flatMap(Collection::stream)
								.filter(pro -> pro.getCategory().equals(category))
								.filter(pro -> pro.getPrice() <= maxPrice && 
										pro.getPrice() >= minPrice && pro.getCountInStock() > 0)
								.collect(Collectors.toList());
		}
		return filteredProducts;
	}
}
