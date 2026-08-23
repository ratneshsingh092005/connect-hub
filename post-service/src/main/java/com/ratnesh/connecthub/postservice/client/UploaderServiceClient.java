package com.ratnesh.connecthub.postservice.client;

import com.ratnesh.connecthub.postservice.config.FeignMultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart; // <-- Import changed to RequestPart
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(
        name = "uploader-service",
        path = "/uploads/file",
        configuration = FeignMultipartSupportConfig.class // <-- 1. Link your encoder configuration
)
public interface UploaderServiceClient {

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE // <-- 2. Explicitly specify multipart/form-data
    )
    ResponseEntity<List<String>> uploadFiles(
            @RequestPart("files") List<MultipartFile> files
    );
}
