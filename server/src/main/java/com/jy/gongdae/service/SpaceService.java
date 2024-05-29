package com.jy.gongdae.service;

import com.jy.gongdae.domain.Images;
import com.jy.gongdae.domain.Space;
import com.jy.gongdae.dto.ImageDto;
import com.jy.gongdae.dto.SpaceDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SpaceService {

    public Long createImage(Space space, List<MultipartFile> multipartFile) throws IOException;
    public List<byte[]> readImage(List<Images> images) throws IOException;
    public Space createSpace(SpaceDto.CreationRequest spaceRequestDto);
    public SpaceDto.Response findSpaceById(Long id);
    public ImageDto findImageBySpace(Long id);
    public List<SpaceDto.ListResponse> findAllAsc();
    public Long updateSpace(Long id, SpaceDto.ModificationRequest spaceModificationRequestDto,
                            List<MultipartFile> multipartFile) throws IOException;
    public Long deleteSpace(Long id);
}