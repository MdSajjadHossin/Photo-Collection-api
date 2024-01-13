package com.springboot.photocollectionapi.repository;

import com.springboot.photocollectionapi.entity.PhotoCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

public interface CategoryRepo extends JpaRepository<PhotoCategory, Integer> {
}
