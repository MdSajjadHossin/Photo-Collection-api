package com.springboot.photocollectionapi.controller;

import com.springboot.photocollectionapi.config.AppConstants;
import com.springboot.photocollectionapi.dto.PostDto;
import com.springboot.photocollectionapi.dto.PostResponse;
import com.springboot.photocollectionapi.exception.ApiResponse;
import com.springboot.photocollectionapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    // create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto createPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    // update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatePost = postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    // delete posts
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted !!!", true);
    }

    // get posts by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
                                                       @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                       @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize){
        PostResponse posts = postService.getPostByUser(userId, pageNumber, pageSize);
        return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
    }

    // get posts by Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
                                                           @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize){
        PostResponse postResponse = postService.getPostByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    // get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_By, required = false) String sortBy,
                                                    @RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIR, required = false) String sortDirection){
        PostResponse postResponse = postService.getAllPost(pageNumber, pageSize, sortBy, sortDirection);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    // get posts by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId){
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<PostDto>(post, HttpStatus.OK);

    }

    // searching
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle (@PathVariable String keyword){
        List<PostDto> searchResult = postService.searchPosts(keyword);
        return new ResponseEntity<List<PostDto>>(searchResult, HttpStatus.OK);

    }
}
