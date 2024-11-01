package com.sujan.tech.dream_shop.service.image;

import com.sujan.tech.dream_shop.model.Image;
import com.sujan.tech.dream_shop.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{
    private final ImageRepository imageRepository;
    @Override
    public Image getImageById(Long id) throws Exception {
        return imageRepository.findById(id).orElseThrow(() -> new Exception("Image not found"));
    }

    @Override
    public void deleteImageById(Long id) {
        return;
    }

    @Override
    public Image saveImage(MultipartFile file, Long productId) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {

    }
}
