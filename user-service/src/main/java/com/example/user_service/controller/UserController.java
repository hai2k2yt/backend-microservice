package com.example.user_service.controller;

import com.example.user_service.dto.GetUserRequestDto;
import com.example.user_service.dto.GetUserResponseDto;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping
  public GetUserResponseDto getUser(@RequestBody GetUserRequestDto request) {
    return userService.getUser(request);
  }
}
