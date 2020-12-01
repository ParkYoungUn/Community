package com.yi.community.repository;

import com.yi.community.model.Board;
import com.yi.community.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
