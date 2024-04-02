package com.jy.gongdae.service;

import com.jy.gongdae.domain.Space;
import com.jy.gongdae.dto.SpaceCreateDto;
import com.jy.gongdae.dto.SpaceReadDto;
import com.jy.gongdae.repository.ImageRepo;
import com.jy.gongdae.repository.SpaceRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        when(spaceRepo.findById(123L))
                .thenReturn(Optional.of(space));

        // when
        SpaceReadDto spaceReadDto = spaceService.findSpaceById(123L);

        // then
        assertEquals(spaceReadDto.getTitle(), space.getTitle());
        assertEquals(spaceReadDto.getAddress(), space.getAddress());
        assertEquals(spaceReadDto.getSector(), space.getSector());
        assertEquals(spaceReadDto.getPrice(), space.getPrice());
        assertEquals(spaceReadDto.getPurpose(), space.getPurpose());

        verify(spaceRepo).findById(123L);
    }

    @Test
    void saveSpaceTest() {
        // given
        when(spaceRepo.save(any(Space.class)))
                .then(returnsFirstArg());

        // when
        Space space = spaceService.createSpace(
                new SpaceCreateDto("test-title", "test-address", "test-sector", 123, 0, null));

        // then
        assertEquals(space.getTitle(), "test-title");
        assertEquals(space.getAddress(), "test-address");
        assertEquals(space.getSector(), "test-sector");
        assertEquals(space.getPrice(), 123);
        assertEquals(space.getPurpose(), 0);

        verify(spaceRepo).save(any());
    }
}