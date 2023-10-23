package com.session_guard.api.service;

import com.session_guard.api.entity.UserToken;
import com.session_guard.api.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    public UserToken findTokenAndUserId(String token, String userId) {
        return userTokenRepository.findByTokenAndUserId(token, userId);
    }
}
