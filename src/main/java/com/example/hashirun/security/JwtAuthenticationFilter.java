package com.example.hashirun.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. リクエストヘッダーから「Authorization」を取り出す
        String authHeader = request.getHeader("Authorization");

        // 2. ヘッダーが「Bearer トークン」の形式かチェック
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // "Bearer "の後の文字列だけ抽出

            // 3. トークンが本物か鑑定！
            if (jwtUtils.validateToken(token)) {
                String email = jwtUtils.getEmailFromToken(token);

                // 4. Spring Securityに「この人は認証済みだよ！」と報告する
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,
                        null, Collections.emptyList());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 5. 次の処理（またはコントローラー）へリクエストを流す
        filterChain.doFilter(request, response);
    }
}
