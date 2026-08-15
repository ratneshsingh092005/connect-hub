package com.ratnesh.connecthub.postservice.controller;

import com.ratnesh.connecthub.postservice.dto.PostCreateRequestDto;
import com.ratnesh.connecthub.postservice.dto.PostDto;
import com.ratnesh.connecthub.postservice.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto ){
        PostDto postDto = postService.createPost(postCreateRequestDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(postDto);
    }

    

}
