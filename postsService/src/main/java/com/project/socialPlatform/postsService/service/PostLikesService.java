package com.project.socialPlatform.postsService.service;

import com.project.socialPlatform.postsService.entity.Post;
import com.project.socialPlatform.postsService.entity.PostLike;
import com.project.socialPlatform.postsService.exception.BadRequestException;
import com.project.socialPlatform.postsService.exception.ResourceNotFoundException;
import com.project.socialPlatform.postsService.repository.PostLikesRepository;
import com.project.socialPlatform.postsService.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikesService {

    private final PostRepository postRepository;
    private final PostLikesRepository postLikesRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void likePost(Long postId) {
        // Todo: Change hardcoded value to dynamic
        Long userId = 1L;
        log.info("User with Id: {}, Liking the post with Id: {}", userId, postId);
        postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post Not Found"));
        boolean hasAlreadyLiked = postLikesRepository.existsByUserIdAndPostId(userId, postId);
        if(hasAlreadyLiked) throw new BadRequestException("you cannot like the post again");
        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikesRepository.save(postLike);

        // Todo: Send Notification to the post owner
    }

    @Transactional
    public void unLikePost(Long postId) {
        // Todo: Change hardcoded value to dynamic
        Long userId = 1L;
        log.info("User with Id: {}, Un Liking the post with Id: {}", userId, postId);
        postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post Not Found"));
        boolean hasAlreadyLiked = postLikesRepository.existsByUserIdAndPostId(userId, postId);
        if(!hasAlreadyLiked) throw new BadRequestException("you cannot unlike the post again");
        postLikesRepository.deleteByUserIdAndPostId(userId,postId);
    }
}
