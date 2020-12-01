package com.yi.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yi.community.model.Board;
import com.yi.community.repository.BoardRepository;

@Controller
@RequestMapping
public class MainBoardController {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/mainlist")
	public String mainlist(Model model) {
		List<Board> boards = boardRepository.findAll();
		model.addAttribute("boards",boards);
		return "board/mainlist";
	}
}
