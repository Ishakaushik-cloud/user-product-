package com.example.postgresql.controller;
import com.example.postgresql.model.User;
import com.example.postgresql.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("{id}")
    public ResponseEntity  getOneUser(@PathVariable  Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid User user){
        return ResponseEntity.status(201).body(userService.createUser(user));
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteById(id));
    }
    @PutMapping("{id}")
    public ResponseEntity updateUser(@PathVariable Long id,@RequestBody User user){
        return ResponseEntity.ok(userService.updateById(id,user));
    }
}



