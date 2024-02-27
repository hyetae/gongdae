package com.jy.gongdae.dto;

import com.jy.gongdae.domain.Images;
import com.jy.gongdae.domain.Space;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class SpaceCreateDto {

    private Long id;
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
