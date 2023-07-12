package com.example.storeservice.service;

import com.example.storeservice.dto.GetStoreDetailResponseDto;
import com.example.storeservice.model.Store;
import com.example.storeservice.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService implements UserDetailsService {

  @Autowired
  private StoreRepository storeRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    return storeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Store not exist"));
  }

  public GetStoreDetailResponseDto getStoreDetail(String username) {
    var user = storeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(""));
    return GetStoreDetailResponseDto
        .builder()
        .id(user.getId())
        .username(user.getUsername())
        .store_name(user.getStore_name())
        .store_description(user.getStore_description())
        .build();

  }

}
