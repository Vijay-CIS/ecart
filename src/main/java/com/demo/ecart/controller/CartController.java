package com.demo.ecart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecart.dto.CartResponseDto;
import com.demo.ecart.dto.ProductRequestDto;
import com.demo.ecart.service.CartService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/carts")
public class CartController {

	private final CartService cartService;

	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}

	/**
	 * This method is used for add products to the cart
	 * 
	 * @param userId
	 * @param requestDto
	 * @return Products added to cart
	 */
	@PostMapping
	public ResponseEntity<CartResponseDto> addProducts(@RequestHeader Long userId,
			@Valid @RequestBody List<ProductRequestDto> requestDto) {
		log.info("inside Cart Controller - addProducts");
		CartResponseDto response = cartService.addProducts(userId, requestDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
