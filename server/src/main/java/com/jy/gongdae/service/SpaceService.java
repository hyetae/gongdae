package com.jy.gongdae.service;

import com.jy.gongdae.domain.Space;
import com.jy.gongdae.dto.SpaceCreateDto;
import com.jy.gongdae.dto.SpaceListReadDto;
import com.jy.gongdae.dto.SpaceReadDto;
import com.jy.gongdae.dto.SpaceUpdateDto;
import com.jy.gongdae.repository.SpaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceService {

    @Autowired
    private SpaceRepo spaceRepo;

    @Transactional
    public Long createSpace(SpaceCreateDto spaceCreateDto) {
        return spaceRepo.save(spaceCreateDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public SpaceReadDto findById(Long id) {
        Space entity = spaceRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new SpaceReadDto(entity);
    }

    @Transactional(readOnly = true)
    public List<SpaceListReadDto> findAllAsc() {
        return spaceRepo.findAllAsc().stream()
                .map(SpaceListReadDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updateSpace(Long id, SpaceUpdateDto spaceUpdateDto) {
        Space entity = spaceRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        entity.update(spaceUpdateDto.getTitle(), spaceUpdateDto.getSector(), spaceUpdateDto.getPrice(), spaceUpdateDto.getPurpose());

        return id;
    }

    @Transactional
    public Long deleteSpace(Long id) {
        spaceRepo.deleteById(id);
        return id;
    }
}
