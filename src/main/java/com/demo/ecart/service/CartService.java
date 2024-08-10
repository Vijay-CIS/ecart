package com.demo.ecart.service;

import java.util.List;

import com.demo.ecart.dto.CartResponseDto;
import com.demo.ecart.dto.ProductRequestDto;

public interface CartService {

	CartResponseDto addProducts(Long userId, List<ProductRequestDto> requestDto);

}
