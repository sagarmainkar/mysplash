package com.mysplash.imagesrv.service.local;

import com.mysplash.imagesrv.dto.ImageResource;
import com.mysplash.imagesrv.repository.ImageRepository;
import com.mysplash.imagesrv.service.AbstractStorageService;
import com.mysplash.imagesrv.service.StorageException;
import com.mysplash.imagesrv.service.StorageProperties;
import com.mysplash.imagesrv.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
@Profile("local")
public class FileSystemStorageService extends AbstractStorageService {

	private final Path rootLocation;



	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public ImageResource store(Integer userId, MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			if(!Files.isDirectory(Paths.get(this.rootLocation.toString()+"/user_"+userId.toString()))) {
				Files.createDirectories(Paths.get(this.rootLocation.toString() + "/user_" + userId.toString()));
			}


			Path destinationFile = this.rootLocation.resolve(
					Paths.get("user_"+userId.toString()+"/"+
								file.getOriginalFilename()))
									.normalize().toAbsolutePath();

			log.info("Destination File :{}",destinationFile);
//			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
//				// This is a security check
//				throw new StorageException(
//						"Cannot store file outside current directory.");
//			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}
			log.info("Successfully stored the file {}",file.getOriginalFilename());

			ImageResource imageResource=ImageResource.builder()
					.userId(userId)
					.imageName(file.getOriginalFilename())
					.imageUrl(this.rootLocation.resolve(file.getOriginalFilename()).toString())
					.build();
			imageRepository.saveAndFlush(imageResource);

			log.info("Image details added to DB");

			return imageResource;
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}



	@Override
	public String load(Integer userId,String filename) {
		return rootLocation.resolve(Paths.get("user"+userId.toString(),filename)).toString();
	}



	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
			log.info("Created Dir : {}",rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
