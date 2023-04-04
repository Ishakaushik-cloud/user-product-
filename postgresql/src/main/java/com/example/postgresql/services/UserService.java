package com.example.postgresql.services;

import com.example.postgresql.model.User;
import com.example.postgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    public UserService() {
    }
    @Autowired
    private UserRepository userRepository;
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        var list =  userRepository.findAll();
        return list;
    }
    public User getUserById(Long Id) {
        Optional<User> userOptional = userRepository.findById(Id);
        User user = userOptional.get();
        user.getProducts();
        return user;
        //orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found"));
    }
    public User deleteById(Long id) {
        User user=this.userRepository.findById(id).get();
        this.userRepository.deleteById(id);
        return user;
    }
    public User updateById(@PathVariable Long id, @RequestBody User user){
        User userToUpdate=this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID does not exist"));;
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        return userRepository.save(userToUpdate);
    }
}



