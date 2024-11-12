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












// ChatGPT の回答は必ずしも正しいとは限りません。重要な情報は確認するようにしてください。