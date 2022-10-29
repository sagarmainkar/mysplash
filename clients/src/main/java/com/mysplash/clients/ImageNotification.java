package com.mysplash.clients;

public record ImageNotification(Integer imageId,
                                Integer userId,
                                String imageUrl,
                                ImageState imageState){


}
