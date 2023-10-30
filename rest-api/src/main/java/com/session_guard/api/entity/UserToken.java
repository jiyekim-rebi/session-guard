package com.session_guard.api.entity;


import lombok.Getter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.*;
import java.time.LocalDateTime;

// 트랜잭션 적용하고 싶으면 RedisTemplate
// timeToLive : 만효 시간을 seconds 단위로 설정
@Getter
@ToString
@RedisHash(value="token", timeToLive = 1800)
public class UserToken {

    @Id
    private String id;

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
