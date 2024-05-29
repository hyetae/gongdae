package com.jy.gongdae.dto;

import com.jy.gongdae.domain.SiteUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
        private String username;

        public UserResponseDto(SiteUser user){
            this.username = user.getUsername();
        }

}
