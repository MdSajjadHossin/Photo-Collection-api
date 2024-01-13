package com.springboot.photocollectionapi.repository;

import com.springboot.photocollectionapi.entity.PhotoCategory;
import com.springboot.photocollectionapi.entity.Post;
import com.springboot.photocollectionapi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(PhotoCategory category);

    Page<Post> findByUserId(Integer userId, Pageable pageable);

    Page<Post> findByCategoryId(Integer categoryId, Pageable pageable);

    List<Post> findByTitleContaining(String title);
}
