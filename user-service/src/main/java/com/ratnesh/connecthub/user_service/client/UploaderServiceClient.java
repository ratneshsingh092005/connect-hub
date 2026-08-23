package com.ratnesh.connecthub.user_service.client;

import com.ratnesh.connecthub.user_service.config.FeignMultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;



@FeignClient(name = "uploader-service", path = "/uploads/file/profile",configuration = FeignMultipartSupportConfig.class)
public interface UploaderServiceClient {


    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE //
    )
    ResponseEntity<String> uploadFile(@RequestPart MultipartFile file);
}

