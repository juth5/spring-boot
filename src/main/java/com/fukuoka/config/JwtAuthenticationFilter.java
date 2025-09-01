package com.fukuoka.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fukuoka.util.JwtUtil;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;

  public JwtAuthenticationFilter(JwtUtil jwtUtil) {
      this.jwtUtil = jwtUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
          throws ServletException, IOException {

      String header = request.getHeader("Authorization");

      if (header != null && header.startsWith("Bearer ")) {
          String token = header.substring(7);
          String username = jwtUtil.validateAndExtractUsername(token);

          if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
              List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

              UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                      username, null, authorities); // ← 権限を追加するならここ
              SecurityContextHolder.getContext().setAuthentication(auth);

          }
      }

      chain.doFilter(request, response);
  }
}
