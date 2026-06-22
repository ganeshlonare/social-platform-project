package com.project.socialPlatform.postsService.service;

import com.project.socialPlatform.postsService.entity.PostLike;
import com.project.socialPlatform.postsService.exception.BadRequestException;
import com.project.socialPlatform.postsService.repository.PostLikeRepository;
import com.project.socialPlatform.postsService.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public void likePost(Long postId) {
        Long userId=1L;
        postRepository.findById(postId).orElseThrow(()-> new BadRequestException("Post not found"));
        boolean ifExists = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if(ifExists) throw new BadRequestException("Already Liked");
        PostLike postLike= new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);

        // Todo: Notification to the post owner
    }

    @Transactional
    public void unlikePost(Long postId){
        Long userId= 1L;
        postRepository.findById(postId).orElseThrow(()-> new BadRequestException("Post not found"));
        boolean ifExists = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if(!ifExists) throw new BadRequestException("You cant unlike the post that you haven't liked");
        postLikeRepository.deleteByUserIdAndPostId(userId, postId);
    }
}
