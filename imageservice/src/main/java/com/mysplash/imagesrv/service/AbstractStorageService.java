package com.mysplash.imagesrv.service;

import com.mysplash.amqp.RabbitMQMessageProducer;
import com.mysplash.clients.ImageNotification;
import com.mysplash.clients.ImageState;
import com.mysplash.imagesrv.dto.ImageResource;
import com.mysplash.imagesrv.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
public class AbstractStorageService implements StorageService{

    @Autowired
    protected ImageRepository imageRepository;

    @Autowired
    protected RabbitMQMessageProducer rabbitMQProducer;
    @Override
    public void init() {

    }

    @Override
    public ImageResource store(Integer userId, MultipartFile file) {
        return null;
    }

    @Override
    public String load(Integer userId, String filename) {
        return null;
    }

    @Override
    public List<ImageResource> getAllResources(Integer userId) {
        log.info("Finding the images for the user {}",userId);
        List<ImageResource> imageResources = imageRepository.findAllByUserId(userId);

        log.info("Found {} images associated with user {}",imageResources.size(),userId);

        return  imageResources;
    }

    public void notifyImageAdded(Integer imageId,Integer userId,String imageUrl){
        log.info("Sending notification for validating image");
        ImageNotification imageNotification = new ImageNotification(imageId,userId,imageUrl, ImageState.ADDED);

        rabbitMQProducer.publish(imageNotification,
                        "internal.exchange",
                        "internal.notification.routing-key");
        log.info("Successfully notified image added {}",imageNotification);
    }
}
