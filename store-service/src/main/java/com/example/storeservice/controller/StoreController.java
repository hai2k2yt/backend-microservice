package com.example.storeservice.controller;

import com.example.storeservice.dto.GetStoreDetailResponseDto;
import com.example.storeservice.model.Role;
import com.example.storeservice.service.StoreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store")
@AllArgsConstructor
@Slf4j
public class StoreController {

  private final StoreService storeService;

  @GetMapping("/{user}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<GetStoreDetailResponseDto> getStoreDetail(@PathVariable String user) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(storeService.getStoreDetail(user));
  }

  @PostMapping
  public ResponseEntity<String> test() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body("TEST");
  }
}
