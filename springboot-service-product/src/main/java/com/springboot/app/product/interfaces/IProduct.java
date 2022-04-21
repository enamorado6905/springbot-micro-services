package com.springboot.app.product.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.springboot.app.product.exceptions.ResourceBadRequestException;
import com.springboot.app.product.exceptions.ResourceNotFoundException;
import com.springboot.app.product.models.ProductModel;

public interface IProduct {
	public abstract ResponseEntity<List<ProductModel>> findAllProducts();

	public abstract ResponseEntity<ProductModel> findProductById(Long productId) throws ResourceNotFoundException;

	public abstract ResponseEntity<ProductModel> saveProduct(ProductModel product) throws ResourceBadRequestException;

	public abstract ResponseEntity<ProductModel> updateProduct(Long productId, ProductModel productDetails)
			throws ResourceBadRequestException, ResourceNotFoundException;

	public abstract ResponseEntity<ProductModel> patchProduct(Long productId, ProductModel productDetails)
			throws ResourceBadRequestException, ResourceNotFoundException;

	public abstract ResponseEntity<ProductModel> deleteProduct(Long productId) throws ResourceNotFoundException;

}
