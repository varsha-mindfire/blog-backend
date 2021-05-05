package com.blogapp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

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

//    @GetMapping("/download/{fileName}")
//    ResponseEntity<Resource> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {
//
//        Resource resource = fileService.downloadFile(fileName);
//        File f=new File(resource.getFile().getAbsolutePath());
//        
//        byte[] data=Files.readAllBytes(f.toPath());
//
//        String mimeType;
//
//        try {
//            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException e) {
//            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
//        }
//        mimeType = mimeType == null ? MediaType.APPLICATION_OCTET_STREAM_VALUE : mimeType;
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(mimeType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName="+resource.getFilename())
//               .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
//                .body(resource);
//        
//    }
    @GetMapping("/download/{fileName}")
    FileDownloadResponse downloadFile(@PathVariable String fileName) throws IOException {
    	String data=fileService.downloadFile(fileName);
    	FileDownloadResponse f=new FileDownloadResponse(data);
    	return f;
    	
  
    }
    }
