package com.jy.gongdae.service;

import com.jy.gongdae.domain.SiteUser;
import com.jy.gongdae.dto.UserRequestDto;
import com.jy.gongdae.dto.UserResponseDto;
import com.jy.gongdae.dto.UserModificationRequestDto;
import com.jy.gongdae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long createUser(UserRequestDto userRequestDto) {
        return userRepository.save(userRequestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id){
        SiteUser entity = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당하는 유저가 없습니다. Id = " + id));

        return new UserResponseDto(entity);
    }

    @Transactional
    public Long updateUser(Long id, UserModificationRequestDto userModificationRequestDto){
        SiteUser entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다. Id" + id));

        entity.update(userModificationRequestDto.getUsername());

        return id;
    }

    @Transactional
    public Long deleteUser(Long id){
        userRepository.deleteById(id);
        return id;
    }
}