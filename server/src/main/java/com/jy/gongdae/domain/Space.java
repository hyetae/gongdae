package com.jy.gongdae.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "space_table")
public class Space extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String address;
    private String sector;
    private int price;
    private int purpose;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @Builder
    public Space(String title, String address, String sector, int purpose, int price) {
        this.title = title;
        this.address = address;
        this.sector = sector;
        this.price = price;
        this.purpose = purpose;
    }

    public void update(String title, String sector, int price, int purpose) {
        this.title = title;
        this.sector = sector;
        this.price = price;
        this.purpose = purpose;
    }
}
