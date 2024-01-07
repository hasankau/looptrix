package com.looptrix.looptrix.services;

import com.looptrix.looptrix.Data.User;
import com.looptrix.looptrix.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    BCryptPasswordEncoder hashPassword = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setPassword(hashPassword.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public boolean deleteUser(long id) {
        try {
            if (userRepository.findUserById(id) != null) {
                userRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public boolean authenticate(User guest) {
        User user = userRepository.findUserByUserName(guest.getUserName());
        if (user == null) {
            user = userRepository.findUserByEmail(guest.getEmail());
        }

        return hashPassword.matches(guest.getPassword(), user.getPassword());
    }

}
