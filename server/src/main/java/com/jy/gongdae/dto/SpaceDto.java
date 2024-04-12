package com.jy.gongdae.dto;

import com.jy.gongdae.domain.Space;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class SpaceDto {

    public static SpaceDto.Response Response;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class CreationRequest {
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

    @Data
    @NoArgsConstructor
    public static class Response {
        private String title;
        private String address;
        private String sector;
        private int price;
        private int purpose;

        public Response(Space space) {
            this.title = space.getTitle();
            this.address = space.getAddress();
            this.sector = space.getSector();
            this.price = space.getPrice();
            this.purpose = space.getPurpose();
        }
    }

    @Data
    @NoArgsConstructor
    public static class ModificationRequest {

        private Long id;
        private String title;
        private String sector;
        private int price;
        private int purpose;

        @Builder
        public ModificationRequest(
                String title, String sector, int price, int purpose) {
            this.title = title;
            this.sector = sector;
            this.price = price;
            this.purpose = purpose;
        }
    }

    @Getter
    public static class ListResponse {

        private Long id;
        private String title;
        private String address;
        private String sector;
        private int price;
        private int purpose;

        public ListResponse(Space space) {
            this.id = space.getId();
            this.title = space.getTitle();
            this.address = space.getAddress();
            this.sector = space.getSector();
            this.price = space.getPrice();
            this.purpose = space.getPurpose();
        }
    }
}