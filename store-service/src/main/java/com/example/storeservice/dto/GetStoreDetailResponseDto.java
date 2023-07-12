package com.example.storeservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetStoreDetailResponseDto {

  private Long id;

  private String username;

  private String store_name;

  private String store_description;

  private String store_logo;
}
