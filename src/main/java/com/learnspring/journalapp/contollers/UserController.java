package com.learnspring.journalapp.contollers;

import com.learnspring.journalapp.entities.UserEntity;
import com.learnspring.journalapp.repository.UserRepository;
import com.learnspring.journalapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{user}")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    // Get User
    @GetMapping("/{username}")
    public ResponseEntity<UserEntity> findByUsername(@PathVariable String username) {
        UserEntity user=userService.findByUsername(username);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    // Get All User
    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> users=userService.getAllUsers();
        if(users==null){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    // Update User
    @PutMapping("/{username}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable String username, @RequestBody UserEntity user) {
        UserEntity userRes=userService.updateUser(username,user);
        if (userRes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRes,HttpStatus.OK);
    }

    // Create User
    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        try{
            UserEntity userEntity=userService.saveUser(user);
            return new ResponseEntity<>(userEntity,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Delete User
}
