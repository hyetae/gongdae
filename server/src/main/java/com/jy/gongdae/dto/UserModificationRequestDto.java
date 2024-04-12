package com.jy.gongdae.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModificationRequestDto {
    private Long id;
    private String username;

    @Builder
    public UserModificationRequestDto(String username){
        this.username = username;
    }
}
