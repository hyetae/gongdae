package com.jy.gongdae.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpaceTest {

    @Test
    @DisplayName("Space create test")
    void createSpace(){
        Space space = Space.builder()
                .title("Test-title")
                .address("Test-address")
                .sector("Test-sector")
                .price(1234)
                .purpose(0)
                .build();

        assertThat(space.getTitle()).isEqualTo("Test-title");
        assertThat(space.getAddress()).isEqualTo("Test-address");
        assertThat(space.getSector()).isEqualTo("Test-sector");
        assertThat(space.getPrice()).isEqualTo(1234);
        assertThat(space.getPurpose()).isEqualTo(0);
    }

    @Test
    @DisplayName("Space update test")
    void updateSpace(){
        Space space = Space.builder()
                .title("Test-title")
                .address("Test-address")
                .sector("Test-sector")
                .price(1234)
                .purpose(0)
                .build();

        space.update("title", "sector", 123, 1);

        assertThat(space.getTitle()).isEqualTo("title");
        assertThat(space.getSector()).isEqualTo("sector");
        assertThat(space.getPrice()).isEqualTo(123);
        assertThat(space.getPurpose()).isEqualTo(1);
    }

}