package com.jy.gongdae.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Images> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private SiteUser siteUser;

    @Builder
    public Space(String title, String address, String sector,
                 int purpose, int price, Images images) {
        this.title = title;
        this.address = address;
        this.sector = sector;
        this.price = price;
        this.purpose = purpose;
    }

    public void update(String title, String sector,
                       int price, int purpose) {
        this.title = title;
        this.sector = sector;
        this.price = price;
        this.purpose = purpose;
    }

    public void createImages(Images images) {
        this.images.add(images);
        if (images.getSpace() != this)
            images.setSpace(this);
    }
}
