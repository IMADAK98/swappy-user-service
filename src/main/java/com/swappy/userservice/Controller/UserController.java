package com.swappy.userservice.Controller;

import com.swappy.userservice.Entity.User;
import com.swappy.userservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String testUserApi(){
        return "Api is working";
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return users.isEmpty() ?
                //TODO modify the response in instances where validation is needed
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found"):
                ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById (@PathVariable Long id){
        User user = userService.getUserById(id);
        return user == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found") :
                ResponseEntity.ok(user);
    }


    @PostMapping("/user")
    public ResponseEntity<User> createUser (@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }


}
