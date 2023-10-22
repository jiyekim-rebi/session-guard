package com.session_guard.api.process;

import java.util.UUID;

public class TokenGenerator {

    public String getToken() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 50);
    }
}
