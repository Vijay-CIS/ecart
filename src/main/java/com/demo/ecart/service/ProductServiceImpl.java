package com.demo.ecart.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.ecart.dto.SaveProductResponseDto;
import com.demo.ecart.entity.Product;
import com.demo.ecart.exception.FileException;
import com.demo.ecart.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	/**
	 * This business logic is to load the data from the excel and save the product
	 * data to the database
	 */
	@Override
	public SaveProductResponseDto saveProduct(MultipartFile file) {
		log.info("inside productServiceImpl - saveProduct");
		if (file == null || file.getOriginalFilename() == null) {
			throw new FileException("File is empty,Please select a file to upload!");
		}
		List<Product> products = new ArrayList<>();
		Workbook workbook = null;
		String filename = file.getOriginalFilename().toLowerCase();
		if (filename.endsWith(".xls")) {
			try {
				workbook = WorkbookFactory.create(file.getInputStream());
			} catch (IOException | EncryptedDocumentException | InvalidFormatException e) {

				e.printStackTrace();
			}
		}
		if (filename.endsWith(".xlsx")) {
			try {
				workbook = WorkbookFactory.create(file.getInputStream());
			} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {

				e.printStackTrace();
			}
		} else {
			log.warn("invalid file format");
			throw new FileException("Please provide valid file format");
		}
		try {
			if (workbook != null) {
				Sheet sheet = workbook.getSheetAt(0);
				workbook.forEach(sheets -> sheet.forEach(row -> {
					if (row.getRowNum() == 0) {
						return; // Skip the header row if it exists
					} else {
						String productName = row.getCell(0).getStringCellValue();

						if (productRepository.existsByProductName(productName)) {
							log.warn("already exist");
							throw new FileException("Product with name '" + productName + "' already exists.");
						}

						Product product = new Product();
						product.setProductName(productName); // Assuming the first column is the product name
						product.setQuantity((int) row.getCell(1).getNumericCellValue());
						product.setPrice(row.getCell(2).getNumericCellValue());

						products.add(product);
					}

				}));

			}

			productRepository.saveAll(products);
		} finally {

			log.info("close");
		}
		SaveProductResponseDto response = new SaveProductResponseDto();
		response.setStatusCode("2001");
		response.setStatusMessage("Products added successfully");
		return response;
	}

}
