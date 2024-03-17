package com.jy.gongdae.service;

import com.jy.gongdae.domain.Images;
import com.jy.gongdae.domain.Space;
import com.jy.gongdae.dto.*;
import com.jy.gongdae.repository.ImageRepo;
import com.jy.gongdae.repository.SpaceRepo;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Transactional
public class SpaceServiceImpl implements SpaceService {

//    private final Logger LOGGER = LoggerFactory.getLogger(SpaceServiceImpl.class);
    private final SpaceRepo spaceRepo;

    private ImageRepo imageRepo;

    @Autowired
    public SpaceServiceImpl(SpaceRepo spaceRepo, ImageRepo imageRepo) {
        this.spaceRepo = spaceRepo;
        this.imageRepo = imageRepo;
    }

    @Value("${SPRING_SERVLET_LOCATION}")
    private String fileDir;

    @Override
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

    @Override
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

    @Override
    public Space createSpace(SpaceCreateDto spaceCreateDto) {
        return spaceRepo.save(spaceCreateDto.toEntity());
    }

    @Override
    public SpaceReadDto findSpaceById(Long id) {
        Space entity = spaceRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new SpaceReadDto(entity);
    }

    @Override
    public SpaceImageDto findImageBySpace(Long id) {
        Space entity = spaceRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new SpaceImageDto(entity);
    }

    @Override
    public List<SpaceListReadDto> findAllAsc() {
        return spaceRepo.findSpaceAllAsc().stream()
                .map(SpaceListReadDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Long updateSpace(Long id, SpaceUpdateDto spaceUpdateDto,
                            List<MultipartFile> multipartFile) throws IOException {
        Space entity = spaceRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        entity.update(spaceUpdateDto.getTitle(), spaceUpdateDto.getSector(),
                spaceUpdateDto.getPrice(), spaceUpdateDto.getPurpose());

        createImage(entity, multipartFile);

        return id;
    }

    @Override
    public Long deleteSpace(Long id) {
        spaceRepo.deleteById(id);
        return id;
    }
}
