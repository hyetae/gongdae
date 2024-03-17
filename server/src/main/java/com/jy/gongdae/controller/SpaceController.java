package com.jy.gongdae.controller;

import com.jy.gongdae.domain.Space;
import com.jy.gongdae.dto.*;
import com.jy.gongdae.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @PostMapping( "/space")
    public ResponseEntity<?> create(@ModelAttribute SpaceCreateDto spaceCreateDto) throws IOException {
        Space entity = spaceService.createSpace(spaceCreateDto);
        List<MultipartFile> file = spaceCreateDto.getImages();
        return new ResponseEntity<>(spaceService.createImage(entity, file), HttpStatus.OK);
    }

    @GetMapping("/space/{id}")
    public ResponseEntity<?> findSpaceById(@PathVariable Long id) {
        SpaceReadDto spaceReadDto = spaceService.findSpaceById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("title", spaceReadDto.getTitle());
        response.put("address", spaceReadDto.getAddress());
        response.put("sector", spaceReadDto.getSector());
        response.put("purpose", spaceReadDto.getPurpose());
        response.put("price", spaceReadDto.getPrice());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "space/{id}/{idx}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> findImageBySpaceId(@PathVariable Long id, @PathVariable int idx) throws IOException {
        SpaceImageDto spaceImageDto = spaceService.findImageBySpace(id);
        List<byte[]> images = spaceService.readImage(spaceImageDto.getImages());

        return new ResponseEntity<>(images.get(idx), HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<SpaceListReadDto> getSpaceList() {
        return spaceService.findAllAsc();
    }

    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> update(@PathVariable Long id, @ModelAttribute SpaceUpdateDto spaceUpdateDto,
                       @ModelAttribute List<MultipartFile> file) throws IOException {
        Long spaceId = spaceService.updateSpace(id, spaceUpdateDto, file);
        return new ResponseEntity<>(spaceId, HttpStatus.OK);
    }

    @DeleteMapping("/space/{id}")
    public Long delete(@PathVariable Long id) {
        spaceService.deleteSpace(id);
        return id;
    }
}
