package com.springboot.photocollectionapi.Security;

import com.springboot.photocollectionapi.entity.User;
import com.springboot.photocollectionapi.exception.ResourceNotFoundException;
import com.springboot.photocollectionapi.exception.UserNotFoundException;
import com.springboot.photocollectionapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // loading user from database by username
        User user = userRepo.findByEmail(username).orElseThrow(()-> new UserNotFoundException("User", "UserName", username));


        return user;
    }
}
