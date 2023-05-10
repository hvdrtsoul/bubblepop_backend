package com.paradiseco.bubblepop.service;

import com.paradiseco.bubblepop.entity.Role;
import com.paradiseco.bubblepop.entity.UserEntity;
import com.paradiseco.bubblepop.exception.UserAlreadyExistsException;
import com.paradiseco.bubblepop.exception.UserNotFoundException;
import com.paradiseco.bubblepop.model.UserModel;
import com.paradiseco.bubblepop.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public UserEntity getById(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if(user == null){
            throw  new UserNotFoundException("User not found");
        }
        return user;
    }

    public List<UserModel> getAllUsers(){
        return userRepo.findAll().stream().map(userEntity -> UserModel.toModel(userEntity)).collect(Collectors.toList());
    }
}
