package com.session_guard.api.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@RedisHash(value="userToken", timeToLive = 1800) // 만료시간 설정(seconds)
public class UserToken {

    @Id // import 주의(org.springframework.data.annotation.Id)
    private String userId;

    @Indexed
    private String token;

    private String device; // 접속한 device
    private String ip; // 접속한 IP

    private LocalDateTime createdAt;

    public UserToken(String userId, String token) {
        LocalDateTime now = LocalDateTime.now();

        this.userId = userId;
        this.token = token;
        this.createdAt = now;
    }

}
