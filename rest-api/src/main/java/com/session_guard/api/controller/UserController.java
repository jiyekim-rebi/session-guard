package com.session_guard.api.controller;

import com.session_guard.api.dao.Response;
import com.session_guard.api.dao.UserDAO;
import com.session_guard.api.entity.User;
import com.session_guard.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/check")
    public ResponseEntity<Object> checkUser(@RequestBody UserDAO reqUser) {
        Response response = new Response();

        logger.info("Check User : search user information");
        logger.info("Check User : Request = " + reqUser.toString());

        if (reqUser == null) {
            response.setReason("request body is empty");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        User user = userService.findUser(reqUser.getUserId(), reqUser.getPassword());
        logger.info("result : " + user.toString());

        if (user == null) {
            response.setReason("user is not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        UserDAO result = new UserDAO();
        result.setId(user.getId());
        result.setUserId(user.getUserId());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
