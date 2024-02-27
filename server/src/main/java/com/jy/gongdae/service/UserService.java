package com.jy.gongdae.service;

import com.jy.gongdae.domain.SiteUser;
import com.jy.gongdae.dto.SpaceReadDto;
import com.jy.gongdae.dto.UserReadDto;
import com.jy.gongdae.dto.UserUpdateDto;
import com.jy.gongdae.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public UserReadDto findById(Long id){
        SiteUser entity = userRepo.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당하는 유저가 없습니다. Id = " + id));

        return new UserReadDto(entity);
    }

    @Transactional
    public Long updateUser(Long id, UserUpdateDto userUpdateDto){
        SiteUser entity = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다. Id" + id));

        entity.update(userUpdateDto.getUsername());

        return id;
    }

    @Transactional
    public Long deleteUser(Long id){
        userRepo.deleteById(id);
        return id;
    }
}