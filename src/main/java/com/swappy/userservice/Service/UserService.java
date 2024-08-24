package com.swappy.userservice.Service;

import com.swappy.userservice.Entity.User;
import com.swappy.userservice.Repo.UserRepo;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public List<User> getAllUsers (){
        return userRepo.findAll();
    }

    public User getUserById(Long id){
        return userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found with this id"));
    }

    public User createUser(User user){
        return userRepo.save(user);
    }

    public User deleteUser(Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    userRepo.deleteById(id);
                    return user;
                })
                .orElseThrow(() -> new RuntimeException("No User with this id"));
    }


    //TODO create an update function



}
