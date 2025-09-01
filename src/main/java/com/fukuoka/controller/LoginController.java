package com.fukuoka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fukuoka.entity.Account;
import com.fukuoka.mapper.AccountMapper;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LoginController {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

  // @GetMapping("/login")
  // public String view(Model model) {
  //   return "login";
  // }

  @PostMapping("/api/signIn")
  public ResponseEntity<?> signIn(@RequestParam String username, @RequestParam String password) {
    Account account = new Account();
    String encodedPassword = passwordEncoder.encode(password);
    account.setUsername(username);
    account.setPassword(encodedPassword);
    accountMapper.insert(account);
    return ResponseEntity.ok().build();
  }
}

