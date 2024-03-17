package com.jy.gongdae.service;

import com.jy.gongdae.domain.Images;
import com.jy.gongdae.domain.Space;
import com.jy.gongdae.dto.SpaceCreateDto;
import com.jy.gongdae.dto.SpaceReadDto;
import com.jy.gongdae.repository.ImageRepo;
import com.jy.gongdae.repository.SpaceRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class SpaceServiceTest {

    private SpaceRepo spaceRepo = Mockito.mock(SpaceRepo.class);
    private ImageRepo imageRepo = Mockito.mock(ImageRepo.class);
    private SpaceServiceImpl spaceService;

    @BeforeEach
    public void setUp() {
        spaceService = new SpaceServiceImpl(spaceRepo, imageRepo);
    }

    @Test
    void getSpaceTest() {
        // given
        Space space = new Space();
        space.setId(123L);
        space.setTitle("test-title");
        space.setAddress("test-address");
        space.setSector("test-sector");
        space.setPrice(123);
        space.setPurpose(0);

        Mockito.when(spaceRepo.findById(123L))
                .thenReturn(Optional.of(space));

        // when
        SpaceReadDto spaceReadDto = spaceService.findSpaceById(123L);

        // then
        Assertions.assertEquals(spaceReadDto.getTitle(), space.getTitle());
        Assertions.assertEquals(spaceReadDto.getAddress(), space.getAddress());
        Assertions.assertEquals(spaceReadDto.getSector(), space.getSector());
        Assertions.assertEquals(spaceReadDto.getPrice(), space.getPrice());
        Assertions.assertEquals(spaceReadDto.getPurpose(), space.getPurpose());

        verify(spaceRepo).findById(123L);
    }

    @Test
    void saveSpaceTest() {
        // given
        Mockito.when(spaceRepo.save(any(Space.class)))
                .then(returnsFirstArg());

        // when
        Space space = spaceService.createSpace(
                new SpaceCreateDto("test-title", "test-address", "test-sector", 123, 0, null));

        // then
        Assertions.assertEquals(space.getTitle(), "test-title");
        Assertions.assertEquals(space.getAddress(), "test-address");
        Assertions.assertEquals(space.getSector(), "test-sector");
        Assertions.assertEquals(space.getPrice(), 123);
        Assertions.assertEquals(space.getPurpose(), 0);

        verify(spaceRepo).save(any());
    }
}