package com.example.storeservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateStoreRequestDto {
  private String store_name;

  private String store_description;

  private String store_logo;
}
