package com.yi.community.controller;

import com.yi.community.model.User;
import com.yi.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
class UserApiController {
	
    @Autowired
    private UserRepository userRepository;

    // Aggregate root

    @GetMapping("/users")  // 전체 조회
    List<User> all() {
    	return userRepository.findAll();
    }

    @PostMapping("/users") // 추가
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // Single item

    @GetMapping("/users/{id}") // id에 해당하는 user 조회
    User one(@PathVariable Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}") // 업데이트
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(user -> {
//                	user.setTitle(newUser.getTitle());
//                	user.setContent(newUser.getContent());
                	
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")  // 삭제
    void deleteUser(@PathVariable Long id) {
    	userRepository.deleteById(id);
    }
}