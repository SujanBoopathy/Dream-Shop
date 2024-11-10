package com.sujan.tech.dream_shop.service.image;

import com.sujan.tech.dream_shop.dto.ImageDto;
import com.sujan.tech.dream_shop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id) throws Exception;
    void deleteImageById(Long id) throws Exception ;
    List<ImageDto>  saveImage(List<MultipartFile> files, Long productId) throws Exception;
    void updateImage(MultipartFile file,Long imageId) throws Exception;
    List<Image> getAllImages();
}
