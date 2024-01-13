package com.springboot.photocollectionapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class PostResponse {

    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;

    private long totalElements;
    private int totalPage;

    private boolean lastPage;
}
