package com.blogapp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

@RestController
@CrossOrigin("http://localhost:4200")
public class FileController {
	@Autowired
	private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    
    //API for uploading a file
    @PostMapping("single/upload")
    FileUploadResponse singleFileUplaod(@RequestParam("file") MultipartFile file) throws IOException {
    	Path filePath = fileService.storeFile(file);
    	String name=filePath.getFileName().toString();
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();
          Resource resource;
         
            resource = new UrlResource(filePath.toUri());

            File f=new File(resource.getFile().getAbsolutePath());
            String filePath1=resource.getFile().getAbsolutePath();
            byte[] data=Files.readAllBytes(f.toPath());

        String contentType = file.getContentType();
        FileUploadResponse response = new FileUploadResponse(name, contentType, url,data,filePath1);

        return response;

    } 
    //API for downloading  a file
    @GetMapping("/download/{fileName}")
    FileDownloadResponse downloadFile(@PathVariable String fileName) throws IOException {
    	String data=fileService.downloadFile(fileName);
    	FileDownloadResponse f=new FileDownloadResponse(data);
    	return f;
    }
}
