package com.session_guard.api.service;

import com.session_guard.api.entity.User;
import com.session_guard.api.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setUserId("rebi");
        user.setPassword("test");
        userRepository.save(user);

        User getUser = userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword());

        Assert.assertEquals(getUser.getUserId(), "rebi");
        Assert.assertEquals(getUser.getPassword(), "test");
    }
}
