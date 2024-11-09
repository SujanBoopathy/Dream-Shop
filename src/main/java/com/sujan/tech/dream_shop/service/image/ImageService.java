package com.sujan.tech.dream_shop.service.image;

import com.sujan.tech.dream_shop.dto.ImageDto;
import com.sujan.tech.dream_shop.exception.ProductNotFoundException;
import com.sujan.tech.dream_shop.model.Image;
import com.sujan.tech.dream_shop.model.Product;
import com.sujan.tech.dream_shop.repository.ImageRepository;
import com.sujan.tech.dream_shop.service.product.IProductService;
import com.sujan.tech.dream_shop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{
    private final ImageRepository imageRepository;
    private final IProductService productService;
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
    public List<ImageDto>  saveImage(List<MultipartFile> files, Long productId) throws Exception {
        try{
            Product product = productService.getProductById(productId);
            List<ImageDto> savedImageDto = new ArrayList<>();
            for (MultipartFile file : files) {
                try {
                    Image image = new Image();
                    image.setFileName(file.getOriginalFilename());
                    image.setFileType(file.getContentType());
                    image.setImage(new SerialBlob(file.getBytes()));
                    image.setProduct(product);

                    String buildDownloadUrl = "/api/v1/images/image/download/";
                    String downloadUrl = buildDownloadUrl+image.getId();
                    image.setDownloadUrl(downloadUrl);
                    Image savedImage = imageRepository.save(image);

                    savedImage.setDownloadUrl(buildDownloadUrl+savedImage.getId());
                    imageRepository.save(savedImage);

                    ImageDto imageDto = new ImageDto();
                    imageDto.setImageId(savedImage.getId());
                    imageDto.setImageName(savedImage.getFileName());
                    imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                    savedImageDto.add(imageDto);

                }   catch(IOException | SQLException e){
                    throw new RuntimeException(e.getMessage());
                }
            }
            return savedImageDto;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        try{
            Image image = getImageById(imageId);
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
