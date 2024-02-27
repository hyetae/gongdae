package com.jy.gongdae.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "image_table")
public class Images extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long size;
    private String name;
    private String path;
    private String extension;

    @ManyToOne
    @JoinColumn(name = "space_id")
    private Space space;

    @Builder
    public Images(Long size, String name, String path, String extension) {
        this.size = size;
        this.name = name;
        this.path = path;
        this.extension = extension;
    }

    public void setSpace(Space space) {
        this.space = space;
        if (!space.getImages().contains(this))
            space.getImages().add(this);
    }
}
