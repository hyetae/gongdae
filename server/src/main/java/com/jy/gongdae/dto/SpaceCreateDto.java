package com.jy.gongdae.dto;

import com.jy.gongdae.domain.Space;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SpaceCreateDto {
    private String title;
    private String address;
    private String sector;
    private int price;
    private int purpose;

    private List<MultipartFile> images;

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
