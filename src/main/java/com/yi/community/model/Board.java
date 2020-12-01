package com.yi.community.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // database autoincriment 설정
	private long id;
	
	//@Size(min=2, max=30, message=("제목은 2자 이상 3자 이하로 입력해주세요.")
	@NotNull
	private String title;
	private String content;
}
