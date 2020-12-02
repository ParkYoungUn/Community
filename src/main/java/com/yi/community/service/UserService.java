package com.yi.community.service;

import com.yi.community.model.Role;
import com.yi.community.model.User;
import com.yi.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user){
        String encodedpassword = passwordEncoder.encode(user.getPassword()); // 사용자가 전달한 비밀번호 암호화
        user.setPassword(encodedpassword); // 암호화 된 저장
        user.setEnabled(true); // 회원가입시 기본적으로 활성화
        Role role = new Role(); 
        role.setId(1l);
        user.getRoles().add(role); // role에 권한 저장
        return userRepository.save(user);
    }

}
