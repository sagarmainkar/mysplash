package com.mysplash.imagesrv.controller;

import com.mysplash.imagesrv.dto.ImageResource;
import com.mysplash.imagesrv.service.StorageFileNotFoundException;
import com.mysplash.imagesrv.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/images")
public class ImageSrvController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/{userId}/upload")
    public ResponseEntity<ImageResource> uploadImage(@PathVariable Integer userId, @RequestParam("image")MultipartFile file) throws IOException {
        log.info("In method ImageSrvController.uploadImage uploading file {}",file.getOriginalFilename());
        return new ResponseEntity<>(storageService.store(userId,file),
                                    HttpStatus.CREATED);

    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<ImageResource>> getAllImages(@PathVariable Integer userId){
        log.info("Fetching all resources for user: {}",userId);

        return new ResponseEntity<>(storageService.getAllResources(userId),HttpStatus.OK);

    }

    @PostConstruct
    public void initialize(){
        storageService.init();
    }


}
