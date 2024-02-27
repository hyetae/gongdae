package com.jy.gongdae.dto;

import com.jy.gongdae.domain.SiteUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReadDto {
        private String username;

        public UserReadDto(SiteUser user){
            this.username = user.getUsername();
        }

}
