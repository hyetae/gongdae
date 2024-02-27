package com.jy.gongdae.service;

import com.jy.gongdae.domain.Images;
import com.jy.gongdae.domain.Space;
import com.jy.gongdae.dto.*;
import com.jy.gongdae.repository.ImageRepo;
import com.jy.gongdae.repository.SpaceRepo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SpaceService {

    @Autowired
    private SpaceRepo spaceRepo;

    @Autowired
    private ImageRepo imageRepo;

    @Value("${SPRING_SERVLET_LOCATION}")
    private String fileDir;

    @Transactional
    public Long createImage(Space space, List<MultipartFile> multipartFile) throws IOException {
        if (!Objects.isNull(multipartFile)) {
            for (MultipartFile file: multipartFile) {
                Long size = file.getSize();
                String name = file.getName();
                String path = fileDir + file.getOriginalFilename();
                String extension = file.getContentType();

                Images images = new Images(size, name, path, extension);
                images.setSpace(space);
                imageRepo.save(images);
                space.createImages(images);

                file.transferTo(new File(path));
            }
        }
        return space.getId();
    }

    public List<byte[]> readImage(List<Images> images) throws IOException {
        List<byte[]> imageByteArray = new ArrayList<>();
        for (Images img: images) {
            InputStream imageStream = new FileInputStream(img.getPath());
            byte[] byteArray = IOUtils.toByteArray(imageStream);
            imageByteArray.add(byteArray);
            imageStream.close();
        }
        return imageByteArray;
    }

    @Transactional
    public Space createSpace(SpaceCreateDto spaceCreateDto) {
        return spaceRepo.save(spaceCreateDto.toEntity());
    }

    @Transactional(readOnly = true)
    public SpaceReadDto findSpaceById(Long id) {
        Space entity = spaceRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new SpaceReadDto(entity);
    }

    @Transactional(readOnly = true)
    public SpaceImageDto findImageBySpace(Long id) {
        Space entity = spaceRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new SpaceImageDto(entity);
    }

    @Transactional(readOnly = true)
    public List<SpaceListReadDto> findAllAsc() {
        return spaceRepo.findSpaceAllAsc().stream()
                .map(SpaceListReadDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updateSpace(Long id, SpaceUpdateDto spaceUpdateDto,
                            List<MultipartFile> multipartFile) throws IOException {
        Space entity = spaceRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        entity.update(spaceUpdateDto.getTitle(), spaceUpdateDto.getSector(),
                spaceUpdateDto.getPrice(), spaceUpdateDto.getPurpose());

        createImage(entity, multipartFile);

        return id;
    }

    @Transactional
    public Long deleteSpace(Long id) {
        spaceRepo.deleteById(id);
        return id;
    }
}
