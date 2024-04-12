package com.jy.gongdae.repository;

import com.jy.gongdae.domain.Space;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpaceRepositoryTest {

    @Autowired
    private SpaceRepository spaceRepository;

    @Test
    @DisplayName("Space DB save")
    void saveSpace() {
        Space space = Space.builder()
                .title("Test-title")
                .address("Test-address")
                .sector("Test-sector")
                .price(1234)
                .purpose(0)
                .build();

        Space result = spaceRepository.save(space);

        assertThat(result.getTitle()).isEqualTo(space.getTitle());
        assertThat(result.getAddress()).isEqualTo(space.getAddress());
        assertThat(result.getSector()).isEqualTo(space.getSector());
        assertThat(result.getPrice()).isEqualTo(space.getPrice());
        assertThat(result.getPurpose()).isEqualTo(space.getPurpose());
    }

//    @Test
//    @DisplayName("Space list test")
//    void SpaceList(){
//        Space space = Space.builder()
//                .title("Test-title")
//                .address("Test-address")
//                .sector("Test-sector")
//                .price(1234)
//                .purpose(0)
//                .build();
//
//        spaceRepo.save(space);
//
//        List<Space> result = spaceRepo.findAll();
//
//        assertThat(result.size()).isEqualTo(1);
//    }
}
