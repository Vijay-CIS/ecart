package com.demo.ecart.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.demo.ecart.dto.CartResponseDto;
import com.demo.ecart.dto.ProductRequestDto;
import com.demo.ecart.entity.Cart;
import com.demo.ecart.entity.CartItems;
import com.demo.ecart.entity.Product;
import com.demo.ecart.entity.User;
import com.demo.ecart.exception.InsufficientQuantityException;
import com.demo.ecart.exception.ProductNotFoundException;
import com.demo.ecart.exception.UserNotFoundException;
import com.demo.ecart.repository.CartItemsRepository;
import com.demo.ecart.repository.CartRepository;
import com.demo.ecart.repository.ProductRepository;
import com.demo.ecart.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

	private final UserRepository userRepo;
	private final ProductRepository productRepo;
	private final CartRepository cartRepo;
	private final CartItemsRepository cartItemsRepo;

	public CartServiceImpl(UserRepository userRepo, ProductRepository productRepo, CartRepository cartRepo,
			CartItemsRepository cartItemsRepo) {
		super();
		this.userRepo = userRepo;
		this.productRepo = productRepo;
		this.cartRepo = cartRepo;
		this.cartItemsRepo = cartItemsRepo;
	}

	/**
	 * This method is used to add products to the cart
	 */
	@Override
	public CartResponseDto addProducts(Long userId, List<ProductRequestDto> requestDto) {
		log.info("Inside cartServiceImpl - addProducts");
		User user = userRepo.findById(userId).orElseThrow(() -> {
			log.error("user not found");
			throw new UserNotFoundException("user not found");
		});

		List<Long> productIds = requestDto.stream().map(ProductRequestDto::getProductId).toList();
		if (requestDto.size() != productIds.stream().distinct().count()) {
			log.warn("duplicate id");
			throw new ProductNotFoundException("duplicate id ");
		}
		SecureRandom random = new SecureRandom();
		Cart cart = new Cart();
		cart.setCartId(random.nextLong(100));
		cart.setUser(user);
		List<Product> products = productRepo.findAllById(productIds);
		List<CartItems> cartItems = new ArrayList<>();
		Map<Long, Product> productMap = products.stream()
				.collect(Collectors.toMap(Product::getProductId, Function.identity()));
		requestDto.forEach(request -> {

			Product product = productMap.get(request.getProductId());
			if (product == null) {
				log.error("product not found");
				throw new ProductNotFoundException("Product not found");
			}
			if (product.getQuantity() < request.getQuantity()) {
				log.warn("insufficient quantity");
				throw new InsufficientQuantityException("insufficient quantity");
			}
			Double totalCost = product.getPrice() * request.getQuantity();
			product.setQuantity(product.getQuantity() - request.getQuantity());
			productRepo.save(product);

			CartItems cartItem = new CartItems();

			cartItem.setCartItemsId(random.nextLong(100));
			cartItem.setCart(cart);
			cartItem.setPrice(totalCost);
			cartItem.setProduct(product);
			cartItem.setQuantity(request.getQuantity());
			cartItems.add(cartItem);

		});

		Double totalAmount = cartItems.stream().mapToDouble(CartItems::getPrice).sum();
		cart.setTotalAmount(totalAmount);
		cart.setDate(LocalDateTime.now());
		cartRepo.save(cart);
		cartItemsRepo.saveAll(cartItems);

		CartResponseDto response = new CartResponseDto();
		response.setStatusCode("2001");
		response.setStatusMessage("Products added to cart");

		return response;
	}

}
