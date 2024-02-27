package com.jy.gongdae.dto;

import com.jy.gongdae.domain.SiteUser;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class UserCreateForm {

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
