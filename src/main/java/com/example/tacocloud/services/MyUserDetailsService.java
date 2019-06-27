package com.example.tacocloud.services;

import com.example.tacocloud.domain.User;
import com.example.tacocloud.repostory.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier
public class MyUserDetailsService implements UserDetailsService {

  private final UserRepository userRepo;

  public MyUserDetailsService(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException(
              "User '" + username + "' not found");
    }

    return user;
  }
}
