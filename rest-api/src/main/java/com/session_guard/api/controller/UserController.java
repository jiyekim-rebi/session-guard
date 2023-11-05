package com.session_guard.api.controller;

import com.session_guard.api.dao.Response;
import com.session_guard.api.dao.UserDAO;
import com.session_guard.api.entity.User;
import com.session_guard.api.entity.UserToken;
import com.session_guard.api.process.TokenGenerator;
import com.session_guard.api.service.UserService;
import com.session_guard.api.service.UserTokenService;
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

    @Autowired
    private UserTokenService userTokenService;

    private TokenGenerator tokenGenerator;


    @PostMapping("/check")
    public ResponseEntity<Object> check(@RequestBody UserDAO reqUser) {
        Response response = new Response();

        if (reqUser == null || "".equals(reqUser.getToken())) {
            response.setReason("request body is empty or token is empty");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        log.debug("Check Token : search token information");
        log.debug("Check Token : " + reqUser.toString());

        UserToken userToken = userTokenService.findTokenAndUserId(reqUser.getToken(), reqUser.getUserId());
        if (userToken == null) {
            response.setReason("token is not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * @Deprecated checkUser : Deprecated (token search로 변경)
     * @param reqUser
     * @return ResponseEntity
     */
경    @PostMapping("/check/info")
    public ResponseEntity<Object> checkUser(@RequestBody UserDAO reqUser) {
        Response response = new Response();

        if (reqUser == null || "".equals(reqUser.getUserId())) {
            response.setReason("request body is empty or user_id is empty");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        log.debug("Check User : search user information");
        log.debug("Check User : " + reqUser.toString());

        User user = userService.findUser(reqUser.getUserId(), reqUser.getPassword());

        if (user == null) {
            response.setReason("user is not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserDAO reqUser) {
        Response response = new Response();

        if (reqUser == null || "".equals(reqUser.getUserId())) {
            response.setReason("request body is empty or user_id is empty");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        log.debug("Login User : search user information");
        log.debug("login user : " + reqUser.toString());

        User user = userService.findUser(reqUser.getUserId(), reqUser.getPassword());

        if (user == null) {
            response.setReason("user is not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        String newToken = tokenGenerator.getToken();

        log.debug("login generate token : " + newToken);
        UserToken userToken = userTokenService.saveToken(new UserToken(user.getUserId(), newToken));

        if (userToken == null) {
            response.setReason("An error occurred while generating token");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        response.setToken(newToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Object> logoutUser(@RequestBody UserDAO reqUser) {
        Response response = new Response();

        if (reqUser == null || "".equals(reqUser.getToken())) {
            response.setReason("request body is empty or token is empty");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        log.debug("Logout : search token information");
        log.debug("Logout : " + reqUser.toString());

        UserToken userToken = userTokenService.findTokenAndUserId(reqUser.getToken(), reqUser.getUserId());
        if (userToken == null) {
            response.setReason("token is not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        userTokenService.deleteToken(new UserToken(reqUser.getUserId(), reqUser.getToken()));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

}
