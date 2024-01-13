package com.springboot.photocollectionapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    private int id;
    private String categoryTitle;
    private String aboutCategory;
}
