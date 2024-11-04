package com.sujan.tech.dream_shop.service.image;

import com.sujan.tech.dream_shop.model.Image;
import com.sujan.tech.dream_shop.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Executable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{
    private final ImageRepository imageRepository;
    @Override
    public Image getImageById(Long id) throws Exception {
        return imageRepository.findById(id).orElseThrow(() -> new Exception("Image not found"));
    }

    public List<Image> getAllImages(){
        return imageRepository.findAll();
    }

    @Override
    public void deleteImageById(Long id) throws Exception {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete,()-> {
            try {
                throw new Exception("Image not Found!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Image saveImage(MultipartFile file, Long productId) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {

    }
}
