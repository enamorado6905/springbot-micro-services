package com.springboot.app.product.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.app.product.exceptions.ResourceBadRequestException;
import com.springboot.app.product.exceptions.ResourceNotFoundException;
import com.springboot.app.product.interfaces.IProduct;
import com.springboot.app.product.models.ProductModel;
import com.springboot.app.product.repositorys.ProductRepository;

@Service
public class ProductService implements IProduct {

	@Autowired
	private ProductRepository productRepository;

	public ResponseEntity<List<ProductModel>> findAllProducts() {
		List<ProductModel> products = productRepository.findAll();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	public ResponseEntity<ProductModel> findProductById(Long productId) throws ResourceNotFoundException {
		ProductModel product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + productId));
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	public ResponseEntity<ProductModel> saveProduct(ProductModel product) throws ResourceBadRequestException {

		if (product.getName() == null || product.getName().equals("")) {
			throw new ResourceBadRequestException("Name is required!");
		}
		if (product.getDescription() == null || product.getDescription().equals("")) {
			throw new ResourceBadRequestException("Name is required!");
		}
		if (product.getPrice() == null) {
			throw new ResourceBadRequestException("Password is required!");
		}

		ProductModel newProduct = productRepository.save(product);
		return new ResponseEntity<>(newProduct, HttpStatus.CREATED);

	}

	public ResponseEntity<ProductModel> updateProduct(Long productId, ProductModel productDetails)
			throws ResourceBadRequestException, ResourceNotFoundException {
		ProductModel product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + productId));

		if (product.getName() == null || product.getName().equals("")) {
			throw new ResourceBadRequestException("Name is required!");
		}
		if (product.getDescription() == null || product.getDescription().equals("")) {
			throw new ResourceBadRequestException("Name is required!");
		}
		if (product.getPrice() == null) {
			throw new ResourceBadRequestException("Password is required!");
		}

		product.setName(productDetails.getName());
		product.setDescription(productDetails.getDescription());
		product.setPrice(productDetails.getPrice());

		final ProductModel patchProduct = productRepository.save(product);
		return new ResponseEntity<>(patchProduct, HttpStatus.ACCEPTED);

	}

	public ResponseEntity<ProductModel> patchProduct(Long productId, ProductModel productDetails)
			throws ResourceBadRequestException, ResourceNotFoundException {
		ProductModel product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + productId));

		if (productDetails.getName() == null && product.getName().equals("") && productDetails.getDescription() == null
				&& productDetails.getDescription().equals("") && productDetails.getPrice() == null) {
			throw new ResourceBadRequestException("All parameters are null!");
		}

		if (productDetails.getName() != null) {
			product.setName(productDetails.getName());

		}
		if (productDetails.getDescription() != null) {
			product.setDescription(productDetails.getDescription());
		}
		if (productDetails.getPrice() != null) {
			product.setPrice(productDetails.getPrice());
		}

		final ProductModel patchProduct = productRepository.save(product);
		return new ResponseEntity<>(patchProduct, HttpStatus.ACCEPTED);

	}

	public ResponseEntity<ProductModel> deleteProduct(Long productId) throws ResourceNotFoundException {
		ProductModel product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + productId));
		productRepository.delete(product);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

}
