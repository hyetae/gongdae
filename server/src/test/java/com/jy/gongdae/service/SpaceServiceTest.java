package com.jy.gongdae.service;

import com.jy.gongdae.domain.Space;
import com.jy.gongdae.dto.SpaceDto;
import com.jy.gongdae.repository.ImageRepository;
import com.jy.gongdae.repository.SpaceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class SpaceServiceTest {

    private SpaceRepository spaceRepository = Mockito.mock(SpaceRepository.class);
    private ImageRepository imageRepository = Mockito.mock(ImageRepository.class);
    private SpaceServiceImpl spaceService;

    @BeforeEach
    public void setUp() {
        spaceService = new SpaceServiceImpl(spaceRepository, imageRepository);
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

        Mockito.when(spaceRepository.findById(123L))
                .thenReturn(Optional.of(space));

        // when
        SpaceDto.Response spaceResponseDto = spaceService.findSpaceById(123L);

        // then
        Assertions.assertEquals(spaceResponseDto.getTitle(), space.getTitle());
        Assertions.assertEquals(spaceResponseDto.getAddress(), space.getAddress());
        Assertions.assertEquals(spaceResponseDto.getSector(), space.getSector());
        Assertions.assertEquals(spaceResponseDto.getPrice(), space.getPrice());
        Assertions.assertEquals(spaceResponseDto.getPurpose(), space.getPurpose());

        verify(spaceRepository).findById(123L);
    }

    @Test
    void saveSpaceTest() {
        // given
        Mockito.when(spaceRepository.save(any(Space.class)))
                .then(returnsFirstArg());

        // when
        Space space = spaceService.createSpace(
                new SpaceDto.CreationRequest("test-title", "test-address", "test-sector", 123, 0, null));

        // then
        Assertions.assertEquals(space.getTitle(), "test-title");
        Assertions.assertEquals(space.getAddress(), "test-address");
        Assertions.assertEquals(space.getSector(), "test-sector");
        Assertions.assertEquals(space.getPrice(), 123);
        Assertions.assertEquals(space.getPurpose(), 0);

        verify(spaceRepository).save(any());
    }
}