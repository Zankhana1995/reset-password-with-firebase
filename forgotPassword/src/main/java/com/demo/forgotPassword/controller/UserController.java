package com.demo.forgotPassword.controller;

import com.demo.forgotPassword.dto.UserDTO;
import com.demo.forgotPassword.domain.User;
import com.demo.forgotPassword.repository.UserRepository;
import com.demo.forgotPassword.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    UserRepository userRepository;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public @ResponseBody
    ResponseEntity addUser(@RequestBody User user) {
        user = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/update/{email}")
    @ResponseBody
    public ResponseEntity updatePassword(@PathVariable("email") String email, @RequestBody UserDTO userdto) {

        User updatedUser = userService.updateUser(email,userdto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @GetMapping("/getUser/{email}")
    public User getUserByEmail(@PathVariable("email") String email){
        return userService.findUserById(email);
    }
}
