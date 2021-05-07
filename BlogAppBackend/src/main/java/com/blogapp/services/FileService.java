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

@Service
public class FileService {
	 private Path fileStoragePath;
	 private String fileStorageLocation;
	 //creating directory in file storage location
	    public  FileService(@Value("${file.storage.location:temp}") String fileStorageLocation) {

	        this.fileStorageLocation = fileStorageLocation;
	        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

	        try {
	            Files.createDirectories(fileStoragePath);
	        } catch (IOException e) {
	            throw new RuntimeException("Issue in creating file directory");
	        }
	    }
	    
	    //storing file in the location
	    public  Path storeFile(MultipartFile file) throws IOException {
	       	String Name = LocalDateTime.now().toString();
	    			Name  =Name.replaceAll(":","_");
	        String fileName = Name+StringUtils.cleanPath(file.getOriginalFilename());    
	        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

	        try {
	            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	            
	            
	        } catch (IOException e) {
	            throw new RuntimeException("Issue in storing the file", e);
	        }
	       
	        return filePath;
	    }
	    
	    // fetching file from storage and converting it into byte array.
	    public String downloadFileTesting(String fileName) throws IOException {

	        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

	        Resource resource;
	        try {
	            resource = new UrlResource(path.toUri());
	    
	            File f=new File(resource.getFile().getAbsolutePath());
	            Files.readAllBytes(f.toPath());

	        } catch (MalformedURLException e) {
	           throw new RuntimeException("Issue in reading the file", e);
	        }

	        if(resource.exists() && resource.isReadable()){

	            File f=new File(resource.getFile().getAbsolutePath());
	            byte[] data=Files.readAllBytes(f.toPath());
	            String encodedString = Base64.getEncoder().encodeToString(data);
	            return encodedString;
	        }else{
	            throw new RuntimeException("the file doesn't exist or not readable");
	        }
	    }
	    
	    public Resource downloadFile(String fileName) throws IOException{

	        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

	        Resource resource;
	        try {
	            resource = new UrlResource(path.toUri());
	    
	            File f=new File(resource.getFile().getAbsolutePath());
	            Files.readAllBytes(f.toPath());

	        } catch (MalformedURLException e) {
	           throw new RuntimeException("Issue in reading the file", e);
	        }

	        if(resource.exists() && resource.isReadable()){

	            return resource;
	        }else{
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
