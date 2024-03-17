package com.jy.gongdae.service;

import com.jy.gongdae.domain.Images;
import com.jy.gongdae.domain.Space;
import com.jy.gongdae.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SpaceService {

    public Long createImage(Space space, List<MultipartFile> multipartFile) throws IOException;
    public List<byte[]> readImage(List<Images> images) throws IOException;
    public Space createSpace(SpaceCreateDto spaceCreateDto);
    public SpaceReadDto findSpaceById(Long id);
    public SpaceImageDto findImageBySpace(Long id);
    public List<SpaceListReadDto> findAllAsc();
    public Long updateSpace(Long id, SpaceUpdateDto spaceUpdateDto,
                            List<MultipartFile> multipartFile) throws IOException;
    public Long deleteSpace(Long id);
}