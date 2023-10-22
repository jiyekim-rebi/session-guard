package com.session_guard.api.repository;

import com.session_guard.api.entity.UserToken;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTokenRepositoryTests {

    @Autowired
    private UserTokenRepository userTokenRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void redisTest() {
        UserToken userToken = new UserToken("rebi", "testtoken");

        userTokenRepository.save(userToken);

        UserToken getToken = userTokenRepository.findByToken(userToken.getToken());

        logger.info("id: {}", getToken.getId());
        logger.info("token: {}", getToken.getToken());
        logger.info("userId: {}", getToken.getUserId());

        Assertions.assertThat(userToken.getUserId()).isEqualTo(getToken.getUserId());
        Assertions.assertThat(userToken.getToken()).isEqualTo(getToken.getToken());
        Assertions.assertThat(userToken.getUserId()).isEqualTo(getToken.getUserId());
    }
}
