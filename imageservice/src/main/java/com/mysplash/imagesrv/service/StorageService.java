package com.mysplash.imagesrv.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;
@Service
public interface StorageService {

	void init();

	String store(Integer userId,MultipartFile file);

	String load(Integer userId,String filename);


}
