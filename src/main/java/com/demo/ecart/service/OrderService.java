package com.demo.ecart.service;

import java.util.List;

import com.demo.ecart.dto.OrderResponseDto;

public interface OrderService {

	OrderResponseDto purchaseItems(Long userId, List<Long> productId);

}
