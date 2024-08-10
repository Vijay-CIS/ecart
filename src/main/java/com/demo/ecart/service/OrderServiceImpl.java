package com.demo.ecart.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.ecart.dto.OrderResponseDto;
import com.demo.ecart.entity.Cart;
import com.demo.ecart.entity.CartItems;
import com.demo.ecart.entity.Order;
import com.demo.ecart.entity.OrderItems;
import com.demo.ecart.exception.CartNotFoundException;
import com.demo.ecart.repository.CartItemsRepository;
import com.demo.ecart.repository.CartRepository;
import com.demo.ecart.repository.OrderItemsRepository;
import com.demo.ecart.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	private final CartRepository cartRepo;
	private final CartItemsRepository cartItemsRepo;
	private final OrderRepository orderRepo;
	private final OrderItemsRepository orderItemsRepo;

	public OrderServiceImpl(CartRepository cartRepo, CartItemsRepository cartItemsRepo, OrderRepository orderRepo,
			OrderItemsRepository orderItemsRepo) {
		super();
		this.cartRepo = cartRepo;
		this.cartItemsRepo = cartItemsRepo;
		this.orderRepo = orderRepo;
		this.orderItemsRepo = orderItemsRepo;
	}

	@Override
	public OrderResponseDto purchaseItems(Long userId, List<Long> productId) {
		log.info("inside OrderServiceImpl - purchaseItems");

		Cart cart = cartRepo.findByUserUserId(userId);
		if (cart == null) {
			log.error("cart not found");
			throw new CartNotFoundException("cart not found");
		}
		System.out.println("cart details: " + cart);
		Order order = new Order();
		order.setUser(cart.getUser());
		List<OrderItems> orderItems = new ArrayList<>();
		for (Long itemId : productId) {
			CartItems cartItem = cartItemsRepo.findByProductProductId(itemId);
			OrderItems orderItem = new OrderItems();
			orderItem.setOrder(order);
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setPrice(cartItem.getPrice());
			orderItem.setProduct(cartItem.getProduct());
			orderItems.add(orderItem);
		    cartItemsRepo.delete(cartItem);

		}
		Double totalAmount = orderItems.stream().mapToDouble(OrderItems::getPrice).sum();
		order.setOrderDate(LocalDateTime.now());
		order.setTotalAmount(totalAmount);
		orderRepo.save(order);
		orderItemsRepo.saveAll(orderItems);

		OrderResponseDto response = new OrderResponseDto();
		response.setStatusCode("2001");
		response.setStatusMessage("Item Purchased, Thank you! Visit again.");
		return response;
	}

}
