package com.jy.gongdae.dto;

import com.jy.gongdae.domain.SiteUser;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class UserRequestDto {

    private Long id;

    private String username;

    private String password;

    private Integer businessCategory;

    public SiteUser toEntity() {
        return SiteUser.builder()
                .username(username)
                .password(password)
                .businessCategory(businessCategory)
                .build();
    }
}
