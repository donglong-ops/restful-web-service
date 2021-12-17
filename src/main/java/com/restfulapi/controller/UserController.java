package com.restfulapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restfulapi.model.User;
import com.restfulapi.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping("usersTest")
    @ResponseBody 
    public String welcome() {
        return "Welcome to User API.";
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId){
    	User user = userRepository.findById(userId).get();
        return ResponseEntity.ok().body(user);
    }
    
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId,
        @Valid @RequestBody User userDetails) {
    	User user = userRepository.findById(userId).get();
    	
    	if(!userDetails.getFullname().isEmpty()) {
        	user.setFullname(userDetails.getFullname());
    	}
    	user.setEmail(userDetails.getEmail());
    	user.setAvatar(userDetails.getAvatar());
    	user.setPhone(userDetails.getPhone());
    	user.setAddress(userDetails.getAddress());
    	user.setRole_id(userDetails.getRole_id());

        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId){
    	User user = userRepository.findById(userId).get();

    	userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}