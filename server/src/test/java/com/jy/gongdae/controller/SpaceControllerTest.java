package com.jy.gongdae.controller;

import com.jy.gongdae.dto.SpaceDto;
import com.jy.gongdae.service.SpaceServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SpaceController.class)
class SpaceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    SpaceServiceImpl spaceService;

    @Test
    @DisplayName("Space 데이터 가져오기 테스트")
    void getSpaceTest() throws Exception {

        Long spaceId = 1L;
        SpaceDto.Response spaceResponseDto = new SpaceDto.Response();
        spaceResponseDto.setTitle("test-title");
        spaceResponseDto.setAddress("test-address");
        spaceResponseDto.setSector("test-sector");
        spaceResponseDto.setPrice(123);
        spaceResponseDto.setPurpose(0);

        when(spaceService.findSpaceById(spaceId)).thenReturn(SpaceDto.Response);

        mvc.perform(
                get("/api/v1/space/{id}", spaceId))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.title").value("test-title"))
                        .andExpect(jsonPath("$.address").value("test-address"))
                        .andExpect(jsonPath("$.sector").value("test-sector"))
                        .andExpect(jsonPath("$.price").value(123))
                        .andExpect(jsonPath("$.purpose").value(0))
                        .andDo(print());

        verify(spaceService).findSpaceById(spaceId);
    }

    @Test
    @DisplayName("Space 데이터 생성 테스트")
    void createSpaceTest() throws Exception {

        MockMultipartFile imageFile = new MockMultipartFile("images", "test-image.jpg",
                MediaType.IMAGE_JPEG_VALUE, "test-image".getBytes());

        SpaceDto.CreationRequest spaceRequestDto = new SpaceDto.CreationRequest();
        spaceRequestDto.setTitle("test-title");
        spaceRequestDto.setAddress("test-address");
        spaceRequestDto.setSector("test-sector");
        spaceRequestDto.setPrice(123);
        spaceRequestDto.setPurpose(0);
        spaceRequestDto.setImages(Collections.singletonList(imageFile));

        when(spaceService.createSpace(any(SpaceDto.CreationRequest.class))).thenReturn(spaceRequestDto.toEntity());

        mvc.perform(
                post("/api/v1/space")
                        .content(spaceRequestDto.toString())
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andDo(print());

        verify(spaceService).createSpace(any(SpaceDto.CreationRequest.class));
    }
}