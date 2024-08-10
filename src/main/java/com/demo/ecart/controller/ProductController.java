package com.demo.ecart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.ecart.dto.SaveProductResponseDto;
import com.demo.ecart.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	/**
	 * This method is used to load the product details from the excel and save the
	 * products to the database
	 * 
	 * @param file
	 * @return SaveProductResponseDto 
	 */
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<SaveProductResponseDto> parseExcelData(@RequestParam(required = false) MultipartFile file) {
		log.info("inside product controller - parseExcelData");
		SaveProductResponseDto response = productService.saveProduct(file);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}