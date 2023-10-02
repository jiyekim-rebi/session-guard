package com.session_guard.api.service;

import com.session_guard.api.entity.UserVO;
import com.session_guard.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserVO findUser(String id, String password) {
        UserVO user = userRepository.findByIdAndPassword(id, password);
        return user;
    }
}
