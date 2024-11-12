package com.example.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    //こちらでsecurityFilterChainメソッドをカスタマイズしている
    //Springコンテナが読んでいる
    //つまり、このメソッドは内部で呼ばれるメソッドを定義している
    //このメソッドの主な目的は、アプリケーションのリクエストに対する認証・認可ポリシーを設定することです。
    //Spring Bootでセキュリティ設定をさらにカスタマイズしたい場合は、SecurityFilterChain を定義して、Spring Securityの設定を追加・変更できます。
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

