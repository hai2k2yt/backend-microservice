package com.example.storeservice.auth;

import com.example.storeservice.config.JwtService;
import com.example.storeservice.model.Role;
import com.example.storeservice.model.Store;
import com.example.storeservice.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final StoreRepository storeRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;

  @PostMapping("/register")
  public AuthenticationResponse register(@RequestBody RegisterRequest request) {
    var user = Store.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .store_name(request.getStore_name())
        .store_description(request.getStore_description())
        .role(Role.USER)
    .build();

    storeRepository.save(user);

    String jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  @PostMapping("/authenticate")
  public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );

    var user = storeRepository
        .findByUsername(request.getUsername())
        .orElseThrow();

    String jwtToken = jwtService.generateToken(user);

    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
