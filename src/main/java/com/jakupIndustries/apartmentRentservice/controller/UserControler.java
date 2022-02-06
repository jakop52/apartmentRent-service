package com.jakupIndustries.apartmentRentservice.controller;

import com.jakupIndustries.apartmentRentservice.entity.User;
import com.jakupIndustries.apartmentRentservice.payload.UserDto;
import com.jakupIndustries.apartmentRentservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserControler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @GetMapping
//    public Optional<User> getUserByAuth(){
//        Auth
//        return this.userRepository.findById()
//    }

    private UserDto convertToDto(Optional<User> user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
    private User convertToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    @GetMapping
    public Optional<User> getUserByAuth(@CurrentSecurityContext(expression="authentication?.name")
                                String email) {
        return this.userRepository.findByEmail(email);
    }
}
