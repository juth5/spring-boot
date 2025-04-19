package com.fukuoka.service;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FirebaseStorageService {
    //使用するfirebaseのstorageを指定
    private final String bucketName = "fukuoa-media.firebasestorage.app";
    //firebaseのstrageのAPI
    private final Storage storage;

    public FirebaseStorageService() throws IOException {
        //firebase全体の初期設定
        FileInputStream serviceAccount = new FileInputStream("/Users/juth5/work/spring-boot-app/src/main/resources/firebase/fukuoa-media-firebase-adminsdk-fbsvc-0ab0f8e398.json");

        //storageAPIにオプションで認証などの設定をして使えるようにする
        this.storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(serviceAccount))
                .build()
                .getService();
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                .setContentType(file.getContentType())
                .build();

        storage.create(blobInfo, file.getBytes());

        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }
}
