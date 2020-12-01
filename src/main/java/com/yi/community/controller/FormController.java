package com.yi.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yi.community.model.Board;
import com.yi.community.repository.BoardRepository;

@Controller
@RequestMapping
public class FormController {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("board", new Board());
		return "board/form";
	}
	
	@PostMapping("/form")
	public String form(@ModelAttribute Board board) {
		boardRepository.save(board);
		return "redirect:/mainlist";
	}
	
}
