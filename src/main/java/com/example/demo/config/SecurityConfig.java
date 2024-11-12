package com.example.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/public/**").permitAll()  // 認証不要のパス
                .anyRequest().authenticated()           // その他のリクエストは認証が必要
            .and()
            .formLogin()
                .loginPage("/login")                    // カスタムログインページの指定
                .permitAll()
            .and()
            .logout()
                .permitAll();

        return http.build();
    }
}
