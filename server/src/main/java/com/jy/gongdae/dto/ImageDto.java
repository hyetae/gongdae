package com.jy.gongdae.dto;

import com.jy.gongdae.domain.Images;
import com.jy.gongdae.domain.Space;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ImageDto {
    private List<Images> images;

    public ImageDto(Space space) {
        this.images = space.getImages();
    }
}
