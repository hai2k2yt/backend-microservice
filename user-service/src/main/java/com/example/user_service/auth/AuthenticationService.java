package com.example.user_service.auth;


import com.example.user_service.config.JwtService;
import com.example.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;

import com.example.user_service.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;


  public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
    var user =
            User
                .builder()
                .username(registerRequestDto.getUsername())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .name(registerRequestDto.getName())
                .build();


    userRepository.save(user);

    String jwtToken = jwtService.generateToken(user);

    return RegisterResponseDto
        .builder()
        .token(jwtToken)
        .build();
  }


  public AuthenticationResponseDto authenticate(AuthenticationRequestDto req) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            req.getUsername(),
            req.getPassword()
        )
    );

    var user = userRepository
        .findByUsername(req.getUsername()).orElseThrow();

    String jwtToken = jwtService.generateToken(user);

    return AuthenticationResponseDto
        .builder()
        .token(jwtToken)
        .build();
  }
}
