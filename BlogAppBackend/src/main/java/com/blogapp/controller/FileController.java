package com.blogapp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.blogapp.dto.response.FileDownloadResponse;
import com.blogapp.dto.response.FileUploadResponse;
import com.blogapp.services.FileService;

/**
 * 
 * @author Varsha
 * @since 15/03/2021
 *
 */
@RestController
@CrossOrigin("http://localhost:4200")
public class FileController {
	@Autowired
	private FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	/**
	 * This method specifies to upload a single file
	 * 
	 * @author Varsha
	 * @params file It contains multipart file object
	 * 
	 * @return response The response contains filename, filetype, the link of file
	 *         storage, base64 data
	 */
	@PostMapping("single/upload")
	FileUploadResponse singleFileUplaod(@RequestParam("file") MultipartFile file) throws IOException {
		Resource resource;
		String name, url;
		byte[] data;
		Path filePath = fileService.storeFile(file);
		name = filePath.getFileName().toString();
		url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(name).toUriString();

		resource = new UrlResource(filePath.toUri());

		File mfile = new File(resource.getFile().getAbsolutePath());
		String filePath1 = resource.getFile().getAbsolutePath();
		data = Files.readAllBytes(mfile.toPath());
		String contentType = file.getContentType();
		FileUploadResponse response = new FileUploadResponse(name, contentType, url, data, filePath1);

		return response;

	}

	/**
	 * This method return file in base64 string
	 * 
	 * @param fileName
	 * @return Response
	 * @throws IOException
	 */
	@GetMapping("/download/{fileName}")
	FileDownloadResponse downloadFile(@PathVariable String fileName) throws IOException {
		String data = fileService.downloadFile(fileName);
		FileDownloadResponse fileDownloadResponse = new FileDownloadResponse(data);
		return fileDownloadResponse;
	}
}
