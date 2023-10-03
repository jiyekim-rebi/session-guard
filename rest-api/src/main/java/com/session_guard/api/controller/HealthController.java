package com.session_guard.api.controller;

import com.session_guard.api.dao.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HealthController {
    @GetMapping("/health")
    public ResponseEntity<Object> health() {
        Response response = new Response();
        response.setReason("Hello, this is session rest-api");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
