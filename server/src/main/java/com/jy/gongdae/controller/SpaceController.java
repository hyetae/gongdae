package com.jy.gongdae.controller;

import com.jy.gongdae.dto.SpaceCreateDto;
import com.jy.gongdae.dto.SpaceListReadDto;
import com.jy.gongdae.dto.SpaceReadDto;
import com.jy.gongdae.dto.SpaceUpdateDto;
import com.jy.gongdae.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/space")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @PostMapping("/create")
    public Long create(@RequestBody SpaceCreateDto spaceCreateDto) {
        return spaceService.createSpace(spaceCreateDto);
    }

    @GetMapping("/{id}")
    public SpaceReadDto findById(@PathVariable Long id) {
        return spaceService.findById(id);
    }

    @GetMapping
    public List<SpaceListReadDto> getSpaceList() {
        return spaceService.findAllAsc();
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody SpaceUpdateDto spaceUpdateDto) {
        return spaceService.updateSpace(id, spaceUpdateDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        spaceService.deleteSpace(id);
        return id;
    }
}
