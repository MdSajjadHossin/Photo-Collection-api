package com.springboot.photocollectionapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PostDto {

    private Integer postId;

    private String title;

    private String imageDescription;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

}
