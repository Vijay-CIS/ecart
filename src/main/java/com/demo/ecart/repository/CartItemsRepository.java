package com.demo.ecart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ecart.entity.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

	//List<CartItems> findAllByProductProductId(List<Long> productId);

//	CartItems findByProductProductId(List<Long> productId);

//	CartItems findByProduct(List<Long> productId);

	//CartItems findByProduct(Long itemId);

	CartItems findByProductProductId(Long itemId);

}
