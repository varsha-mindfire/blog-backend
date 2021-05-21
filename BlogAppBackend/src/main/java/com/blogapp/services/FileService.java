package com.blogapp.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * This class have methods for uploading and fetching multipart file
 * 
 * @author Varsha
 *
 */
@Service
public class FileService {
	private Path fileStoragePath;
	private String fileStorageLocation;

	/**
	 * creating directory in file storage location
	 * 
	 * @param fileStorageLocation
	 * @throws RuntimeException
	 */
	public FileService(@Value("${file.storage.location:temp}") String fileStorageLocation) {

		this.fileStorageLocation = fileStorageLocation;
		fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

		try {
			Files.createDirectories(fileStoragePath);
		} catch (IOException e) {
			throw new RuntimeException("Issue in creating file directory");
		}
	}

	/**
	 * This method stores file in the location
	 * 
	 * @param file
	 * @return Path
	 * @throws IOException
	 */
	public Path storeFile(MultipartFile file) throws IOException {
		String Name = LocalDateTime.now().toString();
		Name = Name.replaceAll(":", "_");

		String fileName = Name + StringUtils.cleanPath(file.getOriginalFilename());
		Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

		try {
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			throw new RuntimeException("Issue in storing the file", e);
		}

		return filePath;
	}

	/**
	 * This file fetches file from storage and converts it into byte array.
	 * 
	 * @param fileName
	 * @return String
	 * @throws RunTimeException
	 */
	public String downloadFile(String fileName) throws IOException {

		Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

		Resource resource;
		try {
			resource = new UrlResource(path.toUri());

			File f = new File(resource.getFile().getAbsolutePath());
			Files.readAllBytes(f.toPath());

		} catch (MalformedURLException e) {
			throw new RuntimeException("Issue in reading the file", e);
		}

		if (resource.exists() && resource.isReadable()) {

			File f = new File(resource.getFile().getAbsolutePath());
			byte[] data = Files.readAllBytes(f.toPath());
			String encodedString = Base64.getEncoder().encodeToString(data);
			return encodedString;
		} else {
			throw new RuntimeException("the file doesn't exist or not readable");
		}
	}

	public Path getFileStoragePath() {
		return fileStoragePath;
	}

	public void setFileStoragePath(Path fileStoragePath) {
		this.fileStoragePath = fileStoragePath;
	}

	public String getFileStorageLocation() {
		return fileStorageLocation;
	}

	public void setFileStorageLocation(String fileStorageLocation) {
		this.fileStorageLocation = fileStorageLocation;
	}

}
