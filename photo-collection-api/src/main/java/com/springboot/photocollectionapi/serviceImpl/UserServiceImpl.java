package com.springboot.photocollectionapi.serviceImpl;

import com.springboot.photocollectionapi.dto.UserDto;
import com.springboot.photocollectionapi.entity.User;
import com.springboot.photocollectionapi.exception.ResourceNotFoundException;
import com.springboot.photocollectionapi.repository.UserRepo;
import com.springboot.photocollectionapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User save = userRepo.save(user);
        return modelMapper.map(save, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateUser = userRepo.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> allUser = userRepo.findAll();
        List<UserDto> userDto = allUser.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDto;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        userRepo.delete(user);

    }
}
