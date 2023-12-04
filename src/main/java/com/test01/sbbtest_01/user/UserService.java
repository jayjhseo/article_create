package com.test01.sbbtest_01.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void create(String username, String nickname, String password) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setNickname(nickname);
        siteUser.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(siteUser);
    }
    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new RuntimeException("user not found");
        }
    }
}
