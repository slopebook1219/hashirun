package com.example.hashirun.repository;

import com.example.hashirun.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//「これはDBへのアクセスを担当するクラスですよ」という印。
@Repository
// extends JpaRepository<User, Long> の正体
// これが魔法の正体です。JpaRepository という巨大な「既製品の道具箱」を継承（コピー）しています。
// User: 「この道具箱は User エンティティを扱う専用ですよ」という指定。
// Long: 「Userの主キー（ID）の型は Long ですよ」という指定。
// これにより、Javaが裏側で自動的に以下のメソッドを使えるように準備してくれます：
// save(user)：保存する（INSERT / UPDATE）
// findAll()：全員取得する（SELECT *）
// findById(id)：IDで1人探す（SELECT ... WHERE id = ?）
// deleteById(id)：削除する（DELETE）
public interface UserRepository extends JpaRepository<User, Long> {
}
