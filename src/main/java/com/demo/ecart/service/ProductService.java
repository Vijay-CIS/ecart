package com.demo.ecart.service;

import org.springframework.web.multipart.MultipartFile;

import com.demo.ecart.dto.SaveProductResponseDto;

public interface ProductService {

	SaveProductResponseDto saveProduct(MultipartFile file);

}
