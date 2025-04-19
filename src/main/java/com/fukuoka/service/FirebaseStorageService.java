package com.fukuoka.service;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.UUID;

@Service
public class FirebaseStorageService {

    private final String bucketName = "fukuoa-media.firebasestorage.app";
    private final Storage storage;

    public FirebaseStorageService() throws IOException {
    String base64 = System.getenv("FIREBASE_CREDENTIALS");  
    InputStream credentialStream;

    if (base64 != null && !base64.isBlank()) {
        byte[] decoded = Base64.getDecoder().decode(base64);
        Path tempFile = Files.createTempFile("firebase", ".json");
        Files.write(tempFile, decoded);
        tempFile.toFile().deleteOnExit();
        credentialStream = new FileInputStream(tempFile.toFile());
    } else {
        // ローカル開発用 fallback
        credentialStream = getClass().getClassLoader().getResourceAsStream("firebase/fukuoa-media-firebase-adminsdk-fbsvc-0ab0f8e398.json");
        if (credentialStream == null) {
            throw new FileNotFoundException("ローカル用の Firebase キーファイルが見つかりません");
        }
    }

    this.storage = StorageOptions.newBuilder()
            .setCredentials(ServiceAccountCredentials.fromStream(credentialStream))
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
