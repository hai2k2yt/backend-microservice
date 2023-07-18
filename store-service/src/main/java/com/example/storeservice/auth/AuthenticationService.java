package com.example.storeservice.auth;

import com.example.storeservice.config.JwtService;
import com.example.storeservice.model.Role;
import com.example.storeservice.model.Store;
import com.example.storeservice.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@AllArgsConstructor
public class AuthenticationService {

  private final StoreRepository storeRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;


  public RegisterResponseDto register(RegisterRequestDto request) {
    var user = Store.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .store_name(request.getStore_name())
        .store_description(request.getStore_description())
        .role(Role.USER)
        .build();

    storeRepository.save(user);

    String jwtToken = jwtService.generateToken(user);
    return RegisterResponseDto
        .builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
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

    return AuthenticationResponseDto.builder()
        .token(jwtToken)
        .build();
  }
}
