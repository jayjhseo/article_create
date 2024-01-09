package com.test01.global.initData;

import com.test01.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class Dev {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner init(UserService userService) {
        return args -> {
//            userService.create("admin@admin.com", "admin", "1234");
//            userService.create("user1@test.com", "user1", "1234");
//            userService.create("user2@test.com", "user2", "1234");
        };
    }
}
