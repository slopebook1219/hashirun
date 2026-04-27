package com.example.hashirun.service;

import com.example.hashirun.entity.User;
import com.example.hashirun.repository.UserRepository;
import com.example.hashirun.security.JwtUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//業務ロジック担当ですという意味
@Service
// 下にある userRepository と passwordEncoder を使える状態にして自動で持ってきてくれます。
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    // spring-boot-starter-securityが与えてくれるエンコード用機能
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    // 具体的なメソッドの定義
    public void registerUser(User user) {
        // 1. パスワードを暗号化してセットし直す
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // 2. Repositoryを使ってDBに保存する
        userRepository.save(user);
    }

    public String login(String email, String password) {
        return userRepository.findByEmail(email)
                .map(dbUser -> {
                    // パスワードが合っているかチェック
                    if (passwordEncoder.matches(password, dbUser.getPassword())) {
                        // ★ここが大事！成功したらトークン（あの長い文字列）を作って返す
                        return jwtUtils.generateToken(dbUser.getEmail());
                    }
                    return null; // パスワードが違えば null
                })
                .orElse(null); // ユーザーがいなければ null
    }

}
