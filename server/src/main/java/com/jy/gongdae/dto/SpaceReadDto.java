package com.jy.gongdae.dto;

import com.jy.gongdae.domain.Space;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SpaceReadDto {

    private String title;
    private String address;
    private String sector;
    private int price;
    private int purpose;

    public SpaceReadDto(Space space) {
        this.title = space.getTitle();
        this.address = space.getAddress();
        this.sector = space.getSector();
        this.price = space.getPrice();
        this.purpose = space.getPurpose();
    }
}
