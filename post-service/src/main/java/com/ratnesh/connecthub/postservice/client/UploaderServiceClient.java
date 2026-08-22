package com.ratnesh.connecthub.postservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "uploader-service",path = "/uploads/file")
public interface UploaderServiceClient {

    @PostMapping
    ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> files);
}
