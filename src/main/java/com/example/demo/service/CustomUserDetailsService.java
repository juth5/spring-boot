package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DataSource dataSource; // データベース接続のためのDataSource

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // SQLクエリを準備
        String sql = "SELECT username, password, role FROM users WHERE username = ?";
        
        try (Connection connection = dataSource.getConnection();
              PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username); // usernameで検索
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                // データベースからユーザー情報を取得
                String dbUsername = rs.getString("username");
                String dbPassword = rs.getString("password"); // ハッシュ化されたパスワード
                String role = rs.getString("role");

                // UserDetailsオブジェクトを生成して返す
                return User.builder()
                        .username(dbUsername)
                        .password(dbPassword)
                        .roles(role) // Spring Securityで使用するために役割を設定
                        .build();
            } else {
                // ユーザーが見つからない場合は例外をスロー
                throw new UsernameNotFoundException("User not found: " + username);
            }

        } catch (SQLException e) {
            // SQLエラー時の例外処理
            throw new RuntimeException("Database error occurred", e);
        }
    }
}
//loadUserByUsernameでは、パスワードの照合は行いません。パスワードの照合は、Spring SecurityがこのUserDetailsオブジェクトを受け取った後に、自動的に内部で行われます。//
//SecurityContextHolder.getContext().setAuthentication(authentication);を呼び出した後、SecurityContextがセッションに保存されます。