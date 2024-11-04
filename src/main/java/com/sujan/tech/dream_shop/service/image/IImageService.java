package com.sujan.tech.dream_shop.service.image;

import com.sujan.tech.dream_shop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id) throws Exception;
    void deleteImageById(Long id) throws Exception ;
    Image saveImage(MultipartFile file, Long productId);
    void updateImage(MultipartFile file,Long imageId);
    List<Image> getAllImages();
}
