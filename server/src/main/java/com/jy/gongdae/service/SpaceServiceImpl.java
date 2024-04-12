package com.jy.gongdae.service;

import com.jy.gongdae.domain.Images;
import com.jy.gongdae.domain.Space;
import com.jy.gongdae.dto.*;
import com.jy.gongdae.repository.ImageRepository;
import com.jy.gongdae.repository.SpaceRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository spaceRepository;

    private ImageRepository imageRepository;

    @Autowired
    public SpaceServiceImpl(SpaceRepository spaceRepository, ImageRepository imageRepository) {
        this.spaceRepository = spaceRepository;
        this.imageRepository = imageRepository;
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
                imageRepository.save(images);
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
    public Space createSpace(SpaceDto.CreationRequest spaceRequestDto) {
        return spaceRepository.save(spaceRequestDto.toEntity());
    }

    @Override
    public SpaceDto.Response findSpaceById(Long id) {
        Space entity = spaceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new SpaceDto.Response(entity);
    }

    @Override
    public ImageDto findImageBySpace(Long id) {
        Space entity = spaceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new ImageDto(entity);
    }

    @Override
    public List<SpaceDto.ListResponse> findAllAsc() {
        return spaceRepository.findSpaceAllAsc().stream()
                .map(SpaceDto.ListResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Long updateSpace(Long id, SpaceDto.ModificationRequest spaceModificationRequestDto,
                            List<MultipartFile> multipartFile) throws IOException {
        Space entity = spaceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        entity.update(spaceModificationRequestDto.getTitle(), spaceModificationRequestDto.getSector(),
                spaceModificationRequestDto.getPrice(), spaceModificationRequestDto.getPurpose());

        createImage(entity, multipartFile);

        return id;
    }

    @Override
    public Long deleteSpace(Long id) {
        spaceRepository.deleteById(id);
        return id;
    }
}
