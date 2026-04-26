package com.example.hashirun.entity;

import jakarta.persistence.*;
import lombok.*;
//DBのテーブルに対応するクラスであることの宣言
@Entity
//MySQL内のusersテーブルに対応しているという宣言
@Table(name = "users")
//これ一行で、全変数の Getter（値を取る）, Setter（値をセットする）, toString（中身を表示する）などを自動生成します。
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    //主キーであることを意味する
    @Id
    //autoincrementの意味
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
}

