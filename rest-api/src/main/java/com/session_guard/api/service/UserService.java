package com.session_guard.api.service;

import com.session_guard.api.entity.User;
import com.session_guard.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUser(String userId, String password) {
        return userRepository.findByUserIdAndPassword(userId, password);
    }
}
