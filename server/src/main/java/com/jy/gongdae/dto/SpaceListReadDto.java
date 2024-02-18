package com.jy.gongdae.dto;

import com.jy.gongdae.domain.Images;
import com.jy.gongdae.domain.Space;
import lombok.Getter;

@Getter
public class SpaceListReadDto {

    private Long id;
    private String title;
    private String address;
    private String sector;
    private int price;
    private int purpose;

    public SpaceListReadDto(Space space) {
        this.id = space.getId();
        this.title = space.getTitle();
        this.address = space.getAddress();
        this.sector = space.getSector();
        this.price = space.getPrice();
        this.purpose = space.getPurpose();
    }
}
