package com.sujan.tech.dream_shop.controller;

import com.sujan.tech.dream_shop.dto.ImageDto;
import com.sujan.tech.dream_shop.model.Image;
import com.sujan.tech.dream_shop.respone.ApiResponse;
import com.sujan.tech.dream_shop.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {
    private final IImageService imageService;

    public ResponseEntity<ApiResponse> saveImage(
            @RequestParam List<MultipartFile> files,
            @RequestParam Long productId
    ){
        try{
            List<ImageDto> imageDtos = imageService.saveImage(files,productId);
            return ResponseEntity.ok(new ApiResponse("Upload Success",imageDtos));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("Upload Failed",e.getMessage())
            );
        }
    }

    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException,Exception {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1,(int) image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+image.getFileName()+"\"")
                .body(resource);
    }
}
