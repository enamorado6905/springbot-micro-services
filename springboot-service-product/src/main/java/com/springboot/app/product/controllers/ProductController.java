package com.springboot.app.product.controllers;
import com.springboot.app.product.exceptions.ResourceBadRequestException;
import com.springboot.app.product.exceptions.ResourceNotFoundException;
import com.springboot.app.product.models.ProductModel;
import com.springboot.app.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<ProductModel>> findAllProducts() {
		return productService.findAllProducts();
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<ProductModel> findProductById(@PathVariable(value = "id") Long productId)
			throws ResourceNotFoundException {
		return productService.findProductById(productId);
	}

	@PostMapping("/products")
	public ResponseEntity<ProductModel> saveProduct(@Validated @RequestBody ProductModel productDetails)
			throws ResourceBadRequestException {
		return productService.saveProduct(productDetails);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<ProductModel> updateProduct(@PathVariable(value = "id") Long productId,
			@Validated @RequestBody ProductModel productDetails)
			throws ResourceBadRequestException, ResourceNotFoundException {
		return productService.updateProduct(productId, productDetails);
	}

	@PatchMapping("/products/{id}")
	public ResponseEntity<ProductModel> patchProduct(@PathVariable(value = "id") Long productId,
			@Validated @RequestBody ProductModel productDetails)
			throws ResourceBadRequestException, ResourceNotFoundException {
		return productService.patchProduct(productId, productDetails);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<ProductModel> deleteProduct(@PathVariable(value = "id") Long productId)
			throws ResourceNotFoundException {
		return productService.deleteProduct(productId);
	}

}
