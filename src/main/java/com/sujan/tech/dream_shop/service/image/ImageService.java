package com.sujan.tech.dream_shop.service.image;

import com.sujan.tech.dream_shop.model.Image;
import org.springframework.web.multipart.MultipartFile;

public class ImageService implements IImageService{
    private final ImageRepository imageRepository;
    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById();
    }

    @Override
    public void deleteImageById(Long id) {

    }

    @Override
    public Image saveImage(MultipartFile file, Long productId) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {

    }
}
