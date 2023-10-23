package com.session_guard.api.controller;

import com.session_guard.api.dao.Response;
import com.session_guard.api.dao.UserDAO;
import com.session_guard.api.entity.UserToken;
import com.session_guard.api.process.TokenGenerator;
import com.session_guard.api.service.UserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserTokenService userTokenService;

    @PostMapping("/check")
    public ResponseEntity<Object> checkToken(@RequestBody UserDAO reqUser) {
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
}
