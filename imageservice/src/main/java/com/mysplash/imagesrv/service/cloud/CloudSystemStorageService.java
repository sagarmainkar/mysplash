package com.mysplash.imagesrv.service.cloud;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.mysplash.imagesrv.configuration.ImageSrvConfig;
import com.mysplash.imagesrv.dto.ImageResource;
import com.mysplash.imagesrv.repository.ImageRepository;
import com.mysplash.imagesrv.service.AbstractStorageService;
import com.mysplash.imagesrv.service.StorageException;
import com.mysplash.imagesrv.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@Profile("kube")
public class CloudSystemStorageService extends AbstractStorageService {

    @Autowired
    private ImageSrvConfig imageSrvConfig;


    private BlobContainerClient blobContainerClient;



    @Override
    public void init() {
        log.info("Connection String: {}",imageSrvConfig.getConnection_string()+"------------");
        log.info("Container Name :{}",imageSrvConfig.getContainer_name()+"----------");

        blobContainerClient = new BlobContainerClientBuilder()
                                    .connectionString(imageSrvConfig.getConnection_string())
                                    .containerName(imageSrvConfig.getContainer_name())
                                    .buildClient();



    }

    @Override
    public ImageResource store(Integer userId,MultipartFile file) {
    try {
        log.info("Creating BlobClient for {}", "user_"+userId+"_"+file.getOriginalFilename());
        BlobClient blobClient = blobContainerClient.getBlobClient("user_"+userId+"_"+file.getOriginalFilename());

        blobClient.upload(file.getInputStream(), file.getSize(), true);
        blobClient.setTags(Collections.singletonMap("user",String.valueOf(userId)));
        String blobUrl = blobClient.getBlobUrl();
        log.info("File Uploaded Successfully the url for file is :{}", blobUrl);
        ImageResource imageResource=ImageResource.builder()
                                    .userId(userId)
                                    .imageName(file.getOriginalFilename())
                                    .imageUrl(blobUrl)
                                    .build();
        imageRepository.saveAndFlush(imageResource);

        log.info("Image details added to DB {}",imageResource);

        notifyImageAdded(imageResource.getId(),userId,imageResource.getImageUrl());
        return imageResource;
    }
    catch (IOException ioException){
            throw new StorageException("Failed to store file.", ioException);
    }

    }

    @Override
    public String load(Integer userId,String filename) {
        BlobClient blobClient = blobContainerClient.getBlobClient("user_"+userId.toString()+"_" +
                "filename");
        return blobClient.getBlobUrl();
    }



}
