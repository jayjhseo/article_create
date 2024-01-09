package com.test01.domain.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public SiteUser create(String username, String nickname, String password) {
        SiteUser siteUser = SiteUser
                .builder()
                .username(username)
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .build();
        return this.userRepository.save(siteUser);
    }
    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new RuntimeException("user not found");
        }
    }
    @Transactional
    public SiteUser whenSocialLogin(String providerTypeCode, String username, String nickname) {
        Optional<SiteUser> opMember = findByUsername(username);

        if (opMember.isPresent()) return opMember.get();

        // 소셜 로그인를 통한 가입시 비번은 없다.
        return create(username, nickname, "");
    }
    private Optional<SiteUser> findByUsername(String username) {
        return userRepository.findByusername(username);
    }

    public boolean checkUsername(String username) {
        Optional<SiteUser> os = this.userRepository.findByusername(username);
        return os.isPresent();
    }
//    public boolean checkPassword(String username, String password) {
//        Optional<SiteUser> om = this.userRepository.findByusername(username);
//        if (om.isPresent()) {
//            SiteUser siteUser = om.get();
//            String encodedPasswordFromDB = siteUser.getPassword();
//            boolean isPasswordMatch = passwordEncoder.matches(password, encodedPasswordFromDB);
//
//            return isPasswordMatch;
//        }
//        return false;
//    }

}
