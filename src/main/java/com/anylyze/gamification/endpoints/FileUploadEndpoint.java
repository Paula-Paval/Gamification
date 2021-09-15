package com.anylyze.gamification.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

@Controller
public class FileUploadEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadEndpoint.class);


    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("fileInput") MultipartFile file) throws IOException {
        byte[] buffer = file.getBytes();
        Files.write(FileSystems.getDefault().getPath(".", file.getOriginalFilename()), buffer);
        logger.info("Uploaded file with name {}", file.getOriginalFilename());
        return new ResponseEntity(HttpStatus.OK);
    }
}
