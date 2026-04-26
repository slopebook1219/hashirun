package com.example.hashirun.service;

import com.example.hashirun.entity.User;
import com.example.hashirun.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//業務ロジック担当ですという意味
@Service
//下にある userRepository と passwordEncoder を使える状態にして自動で持ってきてくれます。
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    // spring-boot-starter-securityが与えてくれるエンコード用機能
    private final PasswordEncoder passwordEncoder;
//具体的なメソッドの定義
    public void registerUser(User user) {
        // 1. パスワードを暗号化してセットし直す
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // 2. Repositoryを使ってDBに保存する
        userRepository.save(user);
    }
}
