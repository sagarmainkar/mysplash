package com.mysplash.imagesrv.service;

import com.mysplash.imagesrv.dto.ImageResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
@Service
public interface StorageService {

	void init();

	ImageResource store(Integer userId, MultipartFile file);

	String load(Integer userId,String filename);

	List<ImageResource> getAllResources(Integer userId);
}
