package com.yi.community.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Board {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @NotNull
    @Size(min=2, max=30, message = "제목은 2자 이상 30자 이하입니다.")
    private String title;
    private String content;
    
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id") // referencedColumnName = "id" : pk값 이므로 생략가능
    private User user;
}
