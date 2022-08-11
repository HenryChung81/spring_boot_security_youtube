package com.example.spring_boot_mybatis_youtube.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.formLogin(login -> login                                 // フォーム認証の設定記述開始
        .loginProcessingUrl("/login")         // ユーザー名、パスワードの送信先URL
            // .loginPage("/login")                       // ログイン画面のURL
            .defaultSuccessUrl("/")            // ログイン成功後のリダイレクト先のURL
            .failureUrl("/login?error") // ログイン失敗後のリダイレクト先のURL
            .permitAll()
    ).logout(logout -> logout                                    // ログアウトの設定記述開始
            .logoutSuccessUrl("/")             // ログアウト成功後のリダイレクト先のURL
    ).authorizeHttpRequests(authz -> authz                      // URLごとの認可設定記述開始
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
            .permitAll()                                       // "/css/**"などはログインなしでもアクセス可能
        .mvcMatchers("/")                        
              .permitAll()                                     // "/"はログインなしでもアクセス可能
        .mvcMatchers("/general")
              .hasRole("GENERAL")                        // "/general"はROLE_GENERALのみアクセス可能
        .mvcMatchers("/admin")
              .hasRole("ADMIN")                          // "/admin"はROLE_ADMINのみアクセス可能
            .anyRequest().authenticated()                     // 他のURLはログイン後のみアクセス可能
    );
      return http.build();
    }

      @Bean
      public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
      }
  
}
