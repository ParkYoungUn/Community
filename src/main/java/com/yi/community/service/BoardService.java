package com.yi.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yi.community.model.Board;
import com.yi.community.model.User;
import com.yi.community.repository.BoardRepository;
import com.yi.community.repository.UserRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository; 
	
	@Autowired
	private UserRepository userRepository;
	
	public Board save(String username, Board board) {
		User user = userRepository.findByUsername(username);
		board.setUser(user);
		return boardRepository.save(board);
	}
	
	
	
	
}
