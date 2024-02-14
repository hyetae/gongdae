package com.jy.gongdae.dto;

import com.jy.gongdae.domain.Space;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpaceCreateDto {

    private Long id;
    private String title;
    private String address;
    private String sector;
    private int price;
    private int purpose;

    public Space toEntity() {
        return Space.builder()
                .title(title)
                .address(address)
                .sector(sector)
                .price(price)
                .purpose(purpose)
                .build();
    }
}
