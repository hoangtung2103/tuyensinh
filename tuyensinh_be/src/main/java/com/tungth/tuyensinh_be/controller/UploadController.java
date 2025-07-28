package com.tungth.tuyensinh_be.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/uploads")
public class UploadController {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${server.host-url}")
    private String hostUrl;

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }

            // Tạo tên file ngẫu nhiên, giữ lại đuôi file
            String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String newFileName = UUID.randomUUID() + "." + filenameExtension;

            File uploadDir = new File(uploadPath).getAbsoluteFile();
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Tạo đường dẫn lưu file
            File saveFile = new File(uploadPath, newFileName);
            file.transferTo(saveFile);

            // Tạo URL trả về cho ảnh
            String imageUrl = hostUrl + "/uploads/" + newFileName;

            return ResponseEntity.ok().body(new UploadResponse(imageUrl));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Upload failed");
        }
    }

    // Inner class để trả JSON
    record UploadResponse(String url) {}
}
