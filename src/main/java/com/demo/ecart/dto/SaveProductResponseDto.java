package com.demo.ecart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveProductResponseDto {
	private String statusCode;
	private String statusMessage;
}
