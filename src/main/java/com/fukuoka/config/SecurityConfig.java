package com.fukuoka.config;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
            .cors(Customizer.withDefaults())  // ← これでCORSを有効化！
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    new AntPathRequestMatcher("/categories"),
                    new AntPathRequestMatcher("/shops/**/favorite")
                ).authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginProcessingUrl("/api/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((request, response, authentication) -> {
                    response.setStatus(HttpServletResponse.SC_OK);

                })
                .failureHandler((request, response, exception) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    System.out.println("ログイン失敗: " + exception.getMessage());
                })
                
            )
        .logout(logout -> logout
            .logoutUrl("/logout")  // ← フロントからこのURLにPOSTすればログアウト
            .logoutSuccessHandler((request, response, authentication) -> {
                response.setStatus(HttpServletResponse.SC_OK);
                System.out.println("ログアウト成功");
            })
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID")
        );
            

        return http.build();
    }

    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf().disable() // CSRF保護を無効化
    //         .authorizeRequests()
    //             .antMatchers("/login", "/logout").permitAll()  // 認証不要のパス
    //             .anyRequest().authenticated()           // その他のリクエストは認証が必要
    //         .and()
    //         .formLogin()
    //             .loginPage("/login")                    // カスタムログインページの指定
    //             .defaultSuccessUrl("/hello", true)   // 認証後のリダイレクト先を指定
    //             .permitAll()
    //         .and()
    //         .logout()
    //         //.logoutUrl("/logout")                         // ログアウトURLを指定
    //             .logoutSuccessUrl("/login?logout")            // ログアウト後のリダイレクト先
    //             .invalidateHttpSession(true)                  // セッションの無効化
    //             .clearAuthentication(true)                    // 認証情報のクリア
    //             .deleteCookies("JSESSIONID")                  // クッキーの削除
    //             .permitAll();

    //     return http.build();
    // }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptを使用してパスワードをエンコード
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

// 認証の流れの詳細
// ログインフォームからのリクエスト送信：

// ユーザーがusernameとpasswordを入力して、/loginにPOSTリクエストを送信します。
// リクエストの内容は、ユーザーが入力したusernameとpasswordの2つです。
// UsernamePasswordAuthenticationFilterがリクエストをキャッチ：

// Spring Securityは、UsernamePasswordAuthenticationFilterというフィルタを使って、/loginへのPOSTリクエストをキャッチします。
// UsernamePasswordAuthenticationFilterは、リクエストからusernameとpasswordを取得し、これらの情報を使って認証を試みます。
// AuthenticationManagerによる認証プロセスの開始：

// UsernamePasswordAuthenticationFilterは、AuthenticationManagerに認証を委ねます。
// AuthenticationManagerは、登録されているUserDetailsServiceを使って、ユーザー情報を取得します。
// UserDetailsServiceのloadUserByUsernameメソッドを呼び出す：

// UserDetailsServiceのloadUserByUsernameメソッドが呼び出され、入力されたusernameに対応するユーザー情報をデータベースなどから取得します。
// このメソッドは、取得したユーザー情報を含むUserDetailsオブジェクト（例：SpringのUserオブジェクト）を返します。
// パスワードの照合：

// Spring Securityは、ユーザーが入力したpasswordと、データベースから取得したハッシュ化されたパスワードをPasswordEncoderを使って照合します。
// 照合が成功すれば認証が成功し、ユーザーはログイン状態になります。
// 認証結果に応じた処理：

// 認証が成功した場合、設定されたリダイレクト先（例：/home）にリダイレクトされます。
// 認証に失敗した場合、再び/login?error=trueにリダイレクトされ、ログインページにエラーメッセージが表示されます。
// まとめ
// <form th:action="@{/login}" method="post"> のフォーム送信後、UsernamePasswordAuthenticationFilterがリクエストをキャッチ。
// UsernamePasswordAuthenticationFilterがAuthenticationManagerに処理を委ね、UserDetailsServiceのloadUserByUsernameでユーザー情報を取得。
// パスワードの照合が行われ、認証の成否に応じてリダイレクトされる。
// この一連のプロセスにより、Spring SecurityがUserDetailsServiceを通じてデータベースと照合し、認証を自動的に行います。


//Spring Securityでは、.loginProcessingUrl()を指定しないと、自動的に/loginが認証処理のエンドポイントとして設定されます。そのため、フォームのaction属性を/loginにすることで認証が実行されます。


//UsernamePasswordAuthenticationFilterは、Spring Securityが自動で設定してくれるフィルタで、通常は開発者が明示的に定義する必要はありません。このフィルタは、デフォルトで**/loginへのPOSTリクエストをキャッチして認証処理を開始**します。
//フォームのname属性がusernameとpasswordでないとSpring Securityが期待する形式と一致しないため、正しく認証が行われません。

// .formLogin()
// .loginPage("/login")
// .usernameParameter("manname")        // カスタムパラメータ名を指定
// .passwordParameter("pass")           // カスタムパラメータ名を指定
// .permitAll();
// こうすると変えられる

// UsernamePasswordAuthenticationFilterでformのpostを受け取って、AuthenticationManagerに処理を委ねるこのAuthenticationManagerが
// UserDetailsServiceを使って認証を開始する
// AuthenticationManagerがUserDetailsServiceのloadUserByUsernameメソッドを呼び出し、usernameに対応するユーザー情報を取得します。


// ChatGPT の回答は必ずしも正しいとは限りません。重要な情報は確認するようにしてください。