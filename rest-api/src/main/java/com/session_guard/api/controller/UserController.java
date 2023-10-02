package com.session_guard.api.controller;

import com.session_guard.api.dao.Response;
import com.session_guard.api.dao.UserDAO;
import com.session_guard.api.entity.UserVO;
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

    // test
    @GetMapping("/check")
    public ResponseEntity<Object> check() {
        Response response = new Response();
        response.setReason("Hello, this is session rest-api");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/")
    public ResponseEntity<Object> getUser(@RequestBody UserVO reqUser) {
        Response response = new Response();
        UserVO user = userService.findUser(reqUser.getUserId(), reqUser.getPassword());
        UserDAO result = new UserDAO();

        if (user == null) {
            response.setReason("user is not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        result.setId(user.getId());
        result.setUserId(user.getUserId());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
