package com.example.hashirun.controller;

import com.example.hashirun.entity.User;
import com.example.hashirun.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//API専用であることの宣言
//このクラスはHTML（画面）を返すのではなく、JSON（データ）を返す窓口ですよ
@RestController
// 住所の決定
// メソッド側は@PostMapping("/register")と書くだけでフルパスになる
@RequestMapping("/api/auth")
// 依存性の注入：DI
// Springが勝手に「使える状態のUserService」をセットしてくれます
@RequiredArgsConstructor
// 国境開放
// localhost:3000なら許可しますよという意味
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    // この窓口には、UserServiceという腕利きの職人が絶対に必要ですという制約を自動でしてくれる。
    // この機能を与えてくれるのが@RequiredArgsConstructor
    private final UserService userService;

    // Next.jsから「POST」という形式でデータが届いた時の処理
    @PostMapping("/register")
    // UserEntitiyの型に自動で詰め替えれる
    public String register(@RequestBody User user) {
        System.out.println(user);
        // 届いたデータをService（職人）に渡して登録してもらう
        userService.registerUser(user);
        return "ユーザー登録が完了しました！";
    }
}
