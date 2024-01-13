package com.springboot.photocollectionapi.service;

import com.springboot.photocollectionapi.dto.PostDto;
import com.springboot.photocollectionapi.dto.PostResponse;

import java.util.List;

public interface PostService {

    // create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // update
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get all posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

    // get post By id
    PostDto getPostById(Integer postId);

    // get post by Category
    PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);

    // get post by User
    PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize);

    //Search Post
    List<PostDto> searchPosts(String keyWord);

}
