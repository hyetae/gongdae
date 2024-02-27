package com.jy.gongdae.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateDto {
    private Long id;
    private String username;

    @Builder
    public UserUpdateDto(String username){
        this.username = username;
    }
}
