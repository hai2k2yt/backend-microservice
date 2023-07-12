package com.example.storeservice.controller;

import com.example.storeservice.dto.GetStoreDetailResponseDto;
import com.example.storeservice.service.StoreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store")
@AllArgsConstructor
@Slf4j
public class StoreController {

  private final StoreService storeService;

  @GetMapping("/{user}")
  public ResponseEntity<GetStoreDetailResponseDto> getStoreDetail(@PathVariable String user) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(storeService.getStoreDetail(user));
  }
}
