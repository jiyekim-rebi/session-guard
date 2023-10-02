package com.session_guard.api.repository;

import com.session_guard.api.entity.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {
    UserVO findByIdAndPassword(String id, String password);
}
