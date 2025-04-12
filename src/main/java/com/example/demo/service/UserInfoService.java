package com.example.demo.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomUserDetails;

import org.springframework.security.core.Authentication;

@Service
public class UserInfoService {
    
    public CustomUserDetails getAuthenticatedUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {

            Object principal = authentication.getPrincipal();

            if (principal instanceof CustomUserDetails) {
                CustomUserDetails userDetails = (CustomUserDetails) principal;
                System.out.println("ddddddddd");
                System.out.println(userDetails.getFullName());
    
                System.out.println("dddddddddd");
                // 追加の加工や処理
                System.out.println("Custom Email: " + userDetails.getEmail());

                return userDetails;
            }
        }
        return null;
    }




}
