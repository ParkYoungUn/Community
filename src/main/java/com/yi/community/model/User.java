package com.yi.community.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String username;
    private String password;
    private Boolean enabled;
    
//  @OneToOne: user - ex) user_detail
//  @OneToMany: user - ex) board
//  @ManyToOne: ex) board - user
//  @ManyToMany: ex) user -role

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false) // orphanRemoval: 기본값 false
    private List<Board> boards = new ArrayList<>();
}
