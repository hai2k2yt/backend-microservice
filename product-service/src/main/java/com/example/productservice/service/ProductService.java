package com.example.productservice.service;


import com.example.productservice.dto.CreateProductRequestDto;
import com.example.productservice.dto.GetProductResponseDto;
import com.example.productservice.dto.UpdateProductRequestDto;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;

  public void createProduct(CreateProductRequestDto createProductRequestDto) {
    Product product = Product
        .builder()
        .name(createProductRequestDto.getName())
        .price(createProductRequestDto.getPrice())
        .description(createProductRequestDto.getDescription())
        .build();

    productRepository.save(product);
  }

  public List<GetProductResponseDto> getAllProducts() {
    List<Product> productList = productRepository.findAll();

    return productList.stream().map(this::mapProductResponse).toList();
  }

  private GetProductResponseDto mapProductResponse(Product product) {
    return GetProductResponseDto
        .builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .description(product.getDescription())
        .build();
  }

  public Optional<GetProductResponseDto> getProductById(Long id) {
    Optional<Product> product = productRepository.findById(id);

    if (product.isPresent()) {
      return product.map(this::mapProductResponse);
    } else {
      throw new IllegalArgumentException("Product not exist by id");
    }
  }

  public void updateProduct(UpdateProductRequestDto updateProductRequestDto) {
    Product product = productRepository.findById(updateProductRequestDto.getId()).orElseThrow(() -> new IllegalArgumentException("Product not exist by id"));

    product.setName(updateProductRequestDto.getName());
    product.setPrice(updateProductRequestDto.getPrice());
    product.setDescription(updateProductRequestDto.getDescription());

    productRepository.save(product);


  }


  public void deleteProductById(Long id) {
    productRepository.deleteById(id);
  }

}
