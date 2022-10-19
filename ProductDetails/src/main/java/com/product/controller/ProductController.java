package com.product.controller;

import java.util.List;
import java.util.Optional;

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

import com.product.model.Product;
import com.product.service.ProductService;
import com.product.service.SequenceGeneratorService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	// save the product
	@PostMapping("/saveProduct")
	public Product save(@RequestBody Product product) {
		product.setId(sequenceGeneratorService.getSequenceNumber(Product.SEQUENCE_NAME));
		return productService.saveProduct(product);
	}

	// find all product
	@GetMapping("/findAllProduct")
	public List<Product> getAll() {
		return productService.getAllProduct();
	}

	// find the product by id
	@GetMapping("/findAllProduct/{id}")
	public Optional<Product> getProduct(@PathVariable int id) {
		return productService.getProductById(id);
	}

	// delete the product by id
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
		return "Delete product with id: " + id;
	}

	// update the product by id
	@PutMapping("/update/{id}")
	public String updateProduct(@RequestBody Product product, @PathVariable int id) {

		productService.updateProduct(product, id);
		return "Update product with id: " + id;

	}

	// find the product by productName
	@GetMapping("/findAllProduct/productName/{productName}")
	public List<Product> getProductByName(@PathVariable String productName) {
		return productService.getProductByProductName(productName);
	}

	// find the product by productCategory
	@GetMapping("/findAllProduct/productCategory/{productCategory}")
	public List<Product> getCropDetailsByType(@PathVariable String productCategory) {
		return productService.getProductByCategory(productCategory);
	}

}
