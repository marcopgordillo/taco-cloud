package com.example.tacocloud.controllers;

import com.example.tacocloud.domain.RegistrationForm;
import com.example.tacocloud.repostory.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

  private final UserRepository userRepo;
  private final PasswordEncoder passwordEncoder;

  public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping
  public String registerForm() {
    return "registration";
  }

  @PostMapping
  public String processRegistration(@Valid RegistrationForm form, Errors errors) {
    if (errors.hasErrors()) {
      return "registration";
    }

    userRepo.save(form.toUser(passwordEncoder));
    return "redirect:/login";
  }
}
