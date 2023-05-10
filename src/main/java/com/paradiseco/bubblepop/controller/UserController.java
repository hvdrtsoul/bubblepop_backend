package com.paradiseco.bubblepop.controller;

import com.paradiseco.bubblepop.entity.UserEntity;
import com.paradiseco.bubblepop.exception.UserAlreadyExistsException;
import com.paradiseco.bubblepop.exception.UserNotFoundException;
import com.paradiseco.bubblepop.repository.UserRepo;
import com.paradiseco.bubblepop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Secured("ADMIN")
    public ResponseEntity getAllUsers(){
        try {
            return ResponseEntity.ok().body(userService.getAllUsers());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Something went wrong...");
        }
    }

}
