package com.session_guard.api.controller;

import com.session_guard.api.dao.Response;
import com.session_guard.api.dao.UserDAO;
import com.session_guard.api.process.TokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("token")
public class TokenController {

    private TokenGenerator tokenGenerator;

    @PostMapping("/check")
    public ResponseEntity<Object> checkToken(@RequestBody UserDAO reqUser) {
        Response response = new Response();

        log.debug("Check Token : search token information");
        log.debug("Check Token : " + reqUser.toString());

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
