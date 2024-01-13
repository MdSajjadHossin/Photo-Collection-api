package com.springboot.photocollectionapi.serviceImpl;

import com.springboot.photocollectionapi.dto.PostDto;
import com.springboot.photocollectionapi.dto.PostResponse;
import com.springboot.photocollectionapi.entity.PhotoCategory;
import com.springboot.photocollectionapi.entity.Post;
import com.springboot.photocollectionapi.entity.User;
import com.springboot.photocollectionapi.exception.ResourceNotFoundException;
import com.springboot.photocollectionapi.repository.CategoryRepo;
import com.springboot.photocollectionapi.repository.PostRepo;
import com.springboot.photocollectionapi.repository.UserRepo;
import com.springboot.photocollectionapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        PhotoCategory category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));


        Post post = modelMapper.map(postDto, Post.class);
        post.setTitle("default.png");
        post.setAddeddate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = postRepo.save(post);
        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        post.setTitle(postDto.getTitle());
        post.setImageDescription(postDto.getImageDescription());

        Post updatePost = postRepo.save(post);
        return modelMapper.map(updatePost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

        Sort sort = null;
        if(sortDirection.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }

        Pageable page = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepo.findAll(page);
        List<Post> allPost = pagePost.getContent();

        List<PostDto> postDtos = allPost.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getNumberOfElements());

        postResponse.setTotalPage(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    @Override
    public PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {
        PhotoCategory category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category", "CategoryId", categoryId));

        Pageable page  = PageRequest.of(pageNumber, pageSize);
        Page<Post> postPage = postRepo.findByCategoryId(categoryId, page);
        List<Post> allPost = postPage.getContent();

        List<PostDto> postDtos = allPost.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalPage(postPage.getTotalPages());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Post> pagePost = postRepo.findByUserId(userId, page);
        List<Post> allPosts = pagePost.getContent();

        List<PostDto> postDtos = allPosts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPage(pagePost.getTotalPages());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    // Search
    @Override
    public List<PostDto> searchPosts(String keyword) {

        List<Post> posts = postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
