package com.bShop.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bShop.model.ImageData;
import com.bShop.repository.StorageRepository;
import com.bShop.upload.ImageUtils;

@Service
public class StorageService {
    @Autowired
    private StorageRepository storageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData save = storageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if(save!=null){
            return "file uploaded successfully: "+file.getOriginalFilename();
        }
        return null;
    }



    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImagebyName = storageRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImagebyName.get().getImageData());
        return images;
    }
}
