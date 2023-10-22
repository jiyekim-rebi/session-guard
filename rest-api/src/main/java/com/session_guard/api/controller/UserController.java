package com.session_guard.api.controller;

import com.session_guard.api.dao.Response;
import com.session_guard.api.dao.UserDAO;
import com.session_guard.api.entity.User;
import com.session_guard.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    // 유저 정보가 맞는지 확인
    @PostMapping("/check")
    public ResponseEntity<Object> checkUser(@RequestBody UserDAO reqUser) {
        Response response = new Response();

        log.debug("Check User : search user information");
        log.debug("Check User : " + reqUser.toString());

        User user = userService.findUser(reqUser.getUserId(), reqUser.getPassword());

        if (user == null) {
            response.setReason("user is not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
