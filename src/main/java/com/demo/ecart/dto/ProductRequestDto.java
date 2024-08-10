package com.demo.ecart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
	@NotNull(message = "product id is mandatory")
	private Long productId;
	@Min(value = 1, message = "quantity can't be negative")
	private Integer quantity;
}
