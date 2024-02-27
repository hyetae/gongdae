package com.jy.gongdae.service;

import com.jy.gongdae.domain.SiteUser;
import com.jy.gongdae.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create (String user_id, String password, Integer businessCategory){
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(user_id);
        siteUser.setBusinessCategory(businessCategory);
        siteUser.setPassword(passwordEncoder.encode(password));
        this.userRepo.save(siteUser);
        return siteUser;
    }
}