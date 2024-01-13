package com.springboot.photocollectionapi.repository;

import com.springboot.photocollectionapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
