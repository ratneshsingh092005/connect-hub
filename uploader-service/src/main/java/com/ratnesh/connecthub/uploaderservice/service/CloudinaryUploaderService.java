package com.ratnesh.connecthub.uploaderservice.service;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CloudinaryUploaderService implements UploaderService {

    private final Cloudinary cloudinary;

    @Override
    public List<String> upload(List<MultipartFile> files) {

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Map uploadResult =
                        cloudinary.uploader()
                                .upload(file.getBytes(), Map.of());

                String url = uploadResult.get("secure_url").toString();

                urls.add(url);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return urls;
    }

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            Map uploadResult =
                    cloudinary.uploader()
                            .upload(file.getBytes(), Map.of());

            return uploadResult.get("secure_url").toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}