package com.session_guard.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RedisHash(value = "token", timeToLive = 30)
@NoArgsConstructor
public class UserToken {

    @Id
    private Long id; // long Id
    private String userId;
    private LocalDateTime createdAt; // 등록시간
    private LocalDateTime expiredAt; // 만료시간

}
