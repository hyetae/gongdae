package com.jy.gongdae.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpaceUpdateDto {

    private Long id;
    private String title;
    private String sector;
    private int price;
    private int purpose;

    @Builder
    public SpaceUpdateDto(String title, String sector,
                          int price, int purpose) {
        this.title = title;
        this.sector = sector;
        this.price = price;
        this.purpose = purpose;
    }
}
