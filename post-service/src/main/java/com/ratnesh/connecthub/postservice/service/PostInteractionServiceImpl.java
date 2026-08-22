package com.ratnesh.connecthub.postservice.service;

import com.ratnesh.connecthub.commonlib.error.BadRequestException;
import com.ratnesh.connecthub.commonlib.error.ResourceNotFoundException;
import com.ratnesh.connecthub.commonlib.security.AuthUtil;
import com.ratnesh.connecthub.postservice.entity.Post;
import com.ratnesh.connecthub.postservice.entity.PostLike;
import com.ratnesh.connecthub.postservice.entity.PostSave;
import com.ratnesh.connecthub.postservice.entity.PostRepost;
import com.ratnesh.connecthub.commonlib.event.PostLikedEvent;
import com.ratnesh.connecthub.commonlib.event.PostRepostedEvent;
import com.ratnesh.connecthub.postservice.repository.PostLikeRepository;
import com.ratnesh.connecthub.postservice.repository.PostRepository;
import com.ratnesh.connecthub.postservice.repository.PostSaveRepository;
import com.ratnesh.connecthub.postservice.repository.PostRepostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PostInteractionServiceImpl implements PostInteractionService{

    private final AuthUtil authUtil;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostSaveRepository postSaveRepository;
    private final PostRepostRepository postRepostRepository;
    private final KafkaTemplate<Long, Object> kafkaTemplate;

    @Override
    public void likePost(Long postId) {
        Long userId = authUtil.getCurrentUserId();
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post",postId.toString()));

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(alreadyLiked) throw new BadRequestException("Post has already been liked");

        PostLike postLike = PostLike.builder()
                .postId(postId)
                .userId(userId)
                .build();

        PostLikedEvent postLikedEvent = PostLikedEvent.builder()
                .postId(postId)
                .postOwnerId(post.getUserId())
                .likedByUserId(userId)
                .createdAt(Instant.now())
                .build();
        postLikeRepository.save(postLike);

        kafkaTemplate.send("post-liked",postLikedEvent);

    }



    @Override
    public void unlikePost(Long postId) {
        Long userId = authUtil.getCurrentUserId();
        postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post",postId.toString()));

        boolean liked = postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(!liked) throw new BadRequestException("Post has not been liked");

        postLikeRepository.deleteByUserIdAndPostId(userId,postId);
    }

    @Override
    public void repostPost(Long postId) {
        Long userId = authUtil.getCurrentUserId();

        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("post", postId.toString()));

        boolean alreadyReposted =
                postRepostRepository.existsByUserIdAndPostId(userId, postId);

        if (alreadyReposted) {
            throw new BadRequestException("Post has already been reposted");
        }

        PostRepost postRepost = PostRepost.builder()
                .postId(postId)
                .userId(userId)
                .build();


        PostRepostedEvent postRepostedEvent = PostRepostedEvent.builder()
                .postId(postId)
                .postOwnerId(post.getUserId())
                .repostedByUserId(userId)
                .createdAt(Instant.now())
                .build();
        postRepostRepository.save(postRepost);

        kafkaTemplate.send("post-reposted",postRepostedEvent);
    }

    @Override
    public void savePost(Long postId) {
        Long userId = authUtil.getCurrentUserId();

        postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("post", postId.toString()));

        boolean alreadySaved =
                postSaveRepository.existsByUserIdAndPostId(userId, postId);

        if (alreadySaved) {
            throw new BadRequestException("Post has already been saved");
        }

        PostSave postSave = PostSave.builder()
                .postId(postId)
                .userId(userId)
                .build();

        postSaveRepository.save(postSave);
    }


    @Override
    public void unsavePost(Long postId) {
        Long userId = authUtil.getCurrentUserId();

        postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("post", postId.toString()));

        boolean saved =
                postSaveRepository.existsByUserIdAndPostId(userId, postId);

        if (!saved) {
            throw new BadRequestException("Post has not been saved");
        }

        postSaveRepository.deleteByUserIdAndPostId(userId, postId);
    }

    @Override
    public void unrepostPost(Long postId) {
        Long userId = authUtil.getCurrentUserId();

        postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("post", postId.toString()));

        boolean reposted =
                postRepostRepository.existsByUserIdAndPostId(userId, postId);

        if (!reposted) {
            throw new BadRequestException("Post has not been reposted");
        }

        postRepostRepository.deleteByUserIdAndPostId(userId, postId);
    }
}
