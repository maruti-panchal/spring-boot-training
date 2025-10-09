package com.learnspring.journalapp.services;

import com.learnspring.journalapp.entities.UserEntity;
import com.learnspring.journalapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Get all users
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Find by id
    public Optional<UserEntity> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    // Create / Save user
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }


    public UserEntity updateUser(String username, UserEntity update) {
        UserEntity oldUser=userRepository.findByUsername(username);
        oldUser.setUsername(!update.getUsername().isEmpty() ?update.getUsername(): oldUser.getUsername());
        oldUser.setPassword(!update.getPassword().isEmpty() ?update.getPassword(): oldUser.getPassword());
        return userRepository.save(oldUser);
    }

//    public boolean deleteById(ObjectId id) {
//        if (!userRepository.existsById(id)) {
//            return false;
//        }
//        userRepository.deleteById(id);
//        return true;
//    }
}
