package com.fukuoka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fukuoka.service.FirebaseStorageService;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        return firebaseStorageService.uploadImage(file);
    }
}
