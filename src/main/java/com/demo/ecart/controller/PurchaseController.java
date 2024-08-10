package com.demo.ecart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecart.dto.OrderResponseDto;
import com.demo.ecart.service.OrderService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

	private final OrderService orderService;

	public PurchaseController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderResponseDto> purchaseItems(@RequestHeader Long userId,
			@RequestBody List<Long> productId) {
		OrderResponseDto response = orderService.purchaseItems(userId, productId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
