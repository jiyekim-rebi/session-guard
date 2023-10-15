package com.session_guard.api.repository;

import com.session_guard.api.entity.UserToken;
import org.springframework.data.repository.CrudRepository;

public interface UserTokenRepository extends CrudRepository<UserToken, String> { }
