package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.mapper.UsersMapper;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Users user = usersMapper.findByUsername(username);

            if (user != null) {
                // データベースからユーザー情報を取得
                String dbUsername = user.getName();
                String dbPassword = user.getPassword();

                // UserDetailsオブジェクトを生成して返す
                return User.builder()
                        .username(dbUsername)
                        .password(dbPassword)
                        .authorities(Collections.emptyList())  // 空のリストを渡す

                        //.roles(role) // Spring Securityで使用するために役割を設定
                        .build();
            } else {
                // ユーザーが見つからない場合は例外をスロー
                throw new UsernameNotFoundException("User not found: " + username);
            }

        } catch (UsernameNotFoundException e) {
            // SQLエラー時の例外処理
            throw new RuntimeException("User not found", e);
        }
    }
}
//loadUserByUsernameでは、パスワードの照合は行いません。パスワードの照合は、Spring SecurityがこのUserDetailsオブジェクトを受け取った後に、自動的に内部で行われます。//
//カスタムUserDetailsクラスのフィールド（例: userNumber, password）は、エンティティクラス（例: Usersクラス）のフィールドと一致させます。例えば、CustomUserDetailsのuserNumberフィールドがUsersエンティティのuserNumberフィールドにリンクされます。
//SecurityContextHolder.getContext().setAuthentication(authentication);を呼び出した後、SecurityContextがセッションに保存されます。
//Spring Securityの認証プロセスの流れ
// ユーザーがログインフォームでユーザー名とパスワードを入力:

// ユーザーがWebアプリケーションのログインフォームに「ユーザー名（またはユーザーID）」と「パスワード」を入力し、送信します。
// Spring SecurityがloadUserByUsernameメソッドを呼び出す:

// Spring Securityは、入力されたユーザー名を使って、UserDetailsServiceインターフェースを実装したクラス（通常はCustomUserDetailsService）のloadUserByUsernameメソッドを呼び出します。
// loadUserByUsernameメソッドにはユーザー名が引数として渡されます。
// loadUserByUsernameメソッドでデータベースからユーザー情報を取得し、UserDetailsオブジェクトを構築:

// loadUserByUsernameメソッドは、受け取ったユーザー名を使ってデータベースからユーザー情報（例: ユーザー名、一意のID、エンコード済みパスワード、権限など）を取得します。
// 取得した情報を基にして、UserDetailsオブジェクトを構築し、Spring Securityに返します。
// Spring Securityがパスワードを照合して認証を行う:

// UserDetailsオブジェクトが返されると、Spring Securityはユーザーがフォームに入力したパスワードをPasswordEncoderでエンコードし、UserDetailsオブジェクトに含まれているデータベースのエンコード済みパスワードと照合します。
// パスワードが一致すれば認証成功、不一致なら認証失敗として処理します。
// 認証成功/失敗後の処理:

// 認証成功した場合、指定されたリダイレクト先ページに遷移します。
// 認証失敗した場合は、再度ログインページに戻され、エラーメッセージが表示されるなどの処理が行われます。
// まとめ
// Spring SecurityのloadUserByUsernameメソッドは、ユーザー名を使ってデータベースからユーザー情報を取得する役割を持ちます。
// UserDetailsオブジェクトが返された後、Spring Securityが入力されたパスワードとデータベースのパスワードを自動的に照合して認証を行います。
// 認証が成功すれば、ユーザーはリクエストしたページにアクセスできます。
// Spring Securityの認証プロセスの流れ
// ユーザーがログインフォームでユーザー名とパスワードを入力:

// ユーザーがWebアプリケーションのログインフォームに「ユーザー名（またはユーザーID）」と「パスワード」を入力し、送信します。
// Spring SecurityがloadUserByUsernameメソッドを呼び出す:

// Spring Securityは、入力されたユーザー名を使って、UserDetailsServiceインターフェースを実装したクラス（通常はCustomUserDetailsService）のloadUserByUsernameメソッドを呼び出します。
// loadUserByUsernameメソッドにはユーザー名が引数として渡されます。
// loadUserByUsernameメソッドでデータベースからユーザー情報を取得し、UserDetailsオブジェクトを構築:

// loadUserByUsernameメソッドは、受け取ったユーザー名を使ってデータベースからユーザー情報（例: ユーザー名、一意のID、エンコード済みパスワード、権限など）を取得します。
// 取得した情報を基にして、UserDetailsオブジェクトを構築し、Spring Securityに返します。
// Spring Securityがパスワードを照合して認証を行う:

// UserDetailsオブジェクトが返されると、Spring Securityはユーザーがフォームに入力したパスワードをPasswordEncoderでエンコードし、UserDetailsオブジェクトに含まれているデータベースのエンコード済みパスワードと照合します。
// パスワードが一致すれば認証成功、不一致なら認証失敗として処理します。
// 認証成功/失敗後の処理:

// 認証成功した場合、指定されたリダイレクト先ページに遷移します。
// 認証失敗した場合は、再度ログインページに戻され、エラーメッセージが表示されるなどの処理が行われます。
// Authenticationオブジェクトの生成:

// 認証が成功すると、Spring Securityは認証情報（UserDetailsオブジェクトに含まれるユーザー名、パスワード、権限など）を基に**Authenticationオブジェクト**を生成します。
// このAuthenticationオブジェクトには、認証されたユーザーの情報が含まれます。
// SecurityContextにAuthenticationオブジェクトを設定:

// 生成されたAuthenticationオブジェクトは、Spring Securityの**SecurityContext**に格納されます。
// Spring Securityは、SecurityContextHolderを介してこのSecurityContextを管理します。これにより、アプリケーション全体で認証情報が保持されます。
// SecurityContextをセッションに保存:

// SecurityContextPersistenceFilterは、リクエストの最後に**SecurityContextをセッションに保存**します。これにより、次のリクエストでも認証情報が維持されるようになります。
// 具体的には、SecurityContextはサーバー側のセッションに保存され、クライアント側には一意のセッションIDが割り当てられます。
// セッションIDをクライアントに返し、クッキーに保存:

// セッションIDはクライアントに送信され、通常は**クッキー（デフォルトではJSESSIONID）**として保存されます。
// クライアント（ブラウザ）は、このセッションIDを含むクッキーを保持し、次回以降のリクエストでサーバーに送り返します。
// 次回のリクエストでセッションを通じた認証情報の確認:

// クライアントが再度リクエストを送ると、セッションIDがクッキーとしてサーバーに送信されます。
// サーバー側のSpring Securityは、このセッションIDを通じてSecurityContextを取得し、認証情報が有効かを確認します。認証済みであれば、セキュリティ保護されたリソースにアクセスできるようになります。
