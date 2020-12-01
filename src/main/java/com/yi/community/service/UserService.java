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
        String encodedpassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedpassword);
        user.setEnabled(true);
        Role role = new Role();
        role.setId(1l);
        user.getRoles().add(role);
        return userRepository.save(user);
    }

}
