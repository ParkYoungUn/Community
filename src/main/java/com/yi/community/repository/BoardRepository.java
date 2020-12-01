package com.yi.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yi.community.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	
}
