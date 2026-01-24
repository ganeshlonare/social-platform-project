package com.project.socialPlatform.postsService.service;

import com.project.socialPlatform.postsService.dto.PostCreateRequestDto;
import com.project.socialPlatform.postsService.dto.PostDto;
import com.project.socialPlatform.postsService.entity.Post;
import com.project.socialPlatform.postsService.exception.ResourceNotFoundException;
import com.project.socialPlatform.postsService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        log.info("Creating post for user with id: {}",userId);
        Post post = modelMapper.map(postCreateRequestDto,Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        log.info("Post created with id: {}",userId);
        return modelMapper.map(post, PostDto.class);
    }

    public PostDto getPost(Long postId) {
        log.info("Getting post with id: {}",postId);
        Post post= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post Not found with id: "+ postId));
        log.info("Post found with id: {}",postId);
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        log.info("Getting all the posts of a user with id: {}",userId);
        List<Post> postList = postRepository.findByUserId(userId);
        return postList.stream()
                .map(post -> modelMapper.map(post,PostDto.class))
                .toList();
    }
}
