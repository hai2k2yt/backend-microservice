package com.example.productservice.controller;


import com.example.productservice.dto.CreateProductRequestDto;
import com.example.productservice.dto.GetProductResponseDto;
import com.example.productservice.dto.UpdateProductRequestDto;
import com.example.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
@Slf4j
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public void createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
    productService.createProduct(createProductRequestDto);
  }

  @GetMapping
  public List<GetProductResponseDto> getAllProduct() {
    return productService.getAllProducts();
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {
    productService.deleteProductById(id);
  }

  @PutMapping
  public void updateProduct(@RequestBody UpdateProductRequestDto updateProductRequestDto) {
    productService.updateProduct(updateProductRequestDto);
  }
}
