package com.springboot.photocollectionapi.serviceImpl;

import com.springboot.photocollectionapi.dto.CategoryDto;
import com.springboot.photocollectionapi.entity.PhotoCategory;
import com.springboot.photocollectionapi.exception.ResourceNotFoundException;
import com.springboot.photocollectionapi.repository.CategoryRepo;
import com.springboot.photocollectionapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        PhotoCategory photoCategory = modelMapper.map(categoryDto, PhotoCategory.class);
        PhotoCategory addCategory = categoryRepo.save(photoCategory);
        return modelMapper.map(addCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        PhotoCategory photoCategory = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));

        photoCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        photoCategory.setAboutCategory(categoryDto.getAboutCategory());

        PhotoCategory updateCategory = categoryRepo.save(photoCategory);
        return modelMapper.map(updateCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        PhotoCategory photoCategory = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        categoryRepo.delete(photoCategory);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        PhotoCategory photoCategory = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));

        return modelMapper.map(photoCategory, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<PhotoCategory> categoryList = categoryRepo.findAll();
        List<CategoryDto> categoryDto = categoryList.stream().map((cat) -> modelMapper.map(categoryList, CategoryDto.class)).collect(Collectors.toList());

        return categoryDto;
    }
}
