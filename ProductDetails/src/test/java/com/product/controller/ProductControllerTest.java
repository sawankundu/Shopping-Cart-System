package com.product.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.product.model.Product;
import com.product.service.ProductService;
import com.product.service.impl.ProductServiceImpl;
import com.product.util.ProductDetailsUtilTest;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;

	@Mock
	private ProductServiceImpl productServiceImpl;

	private Product productResponse = null;

	/**
	 * setup-define the common mocks
	 */
	@Before
	public void setup() {
		productResponse = ProductDetailsUtilTest.jsonToModel("mocks/findAllProductResponse.json", Product.class);
	}

	/**
	 * find all product
	 */
	@Test
	public void findAllProduct() {
		List<Product> findAllProductResponse = new ArrayList<>();
		findAllProductResponse.add(productResponse);
		Mockito.when(productServiceImpl.getAllProduct()).thenReturn(findAllProductResponse);
		List<Product> productResponse = productController.getAll();
		assertNotNull(productResponse);
		Assert.assertEquals(null, productResponse.get(0).getPrice());
	}
}
