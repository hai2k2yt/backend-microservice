package com.example.user_service.service;

import com.example.user_service.dto.GetUserRequestDto;
import com.example.user_service.dto.GetUserResponseDto;

import com.example.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public GetUserResponseDto getUser(GetUserRequestDto request) {
    var user = userRepository.findByUsername(request.getUsername()).orElseThrow();

    return GetUserResponseDto
        .builder()
        .username(user.getUsername())
        .name(user.getName())
        .user_avatar(user.getUser_avatar())
        .build();
  }

}