package com.example.user_service.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public RegisterResponseDto register(@RequestBody RegisterRequestDto request) {
    return authenticationService.register(request);
  }

  @PostMapping("/authenticate")
  public AuthenticationResponseDto authenticate(@RequestBody AuthenticationRequestDto request) {
    return authenticationService.authenticate(request);
  }
}
