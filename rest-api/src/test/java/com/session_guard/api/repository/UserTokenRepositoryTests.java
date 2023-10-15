package com.session_guard.api.repository;

import com.session_guard.api.entity.UserToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTokenRepositoryTests {

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Test
    void redisTest() {
        UserToken userToken = new UserToken("rebi");

        userTokenRepository.save(userToken);

        userTokenRepository.findById(userToken.getId());

        userTokenRepository.count();
    }
}
