package com.example.productservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class GetProductResponseDto {
  private Long id;

  private String name;

  private BigDecimal price;

  private String description;
}
