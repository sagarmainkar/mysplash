package com.mysplash.imagesrv.repository;

import com.mysplash.imagesrv.dto.ImageResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageResource,Integer> {

    List<ImageResource> findAllByUserId(Integer userId);
}
