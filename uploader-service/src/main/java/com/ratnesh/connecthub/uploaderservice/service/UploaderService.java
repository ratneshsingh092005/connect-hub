package com.ratnesh.connecthub.uploaderservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploaderService {

    List<String> upload(List<MultipartFile> files);
}
