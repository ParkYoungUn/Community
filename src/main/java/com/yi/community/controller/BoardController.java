package com.yi.community.controller;

import com.yi.community.model.Board;
import com.yi.community.repository.BoardRepository;
import com.yi.community.service.BoardService;
import com.yi.community.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size =5) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage =Math.max(1, boards.getPageable().getPageNumber() -4);
        int endPage =Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() +4);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards",boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board",board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String postForm(@Valid Board board, BindingResult bindingResult, Authentication authentication) { // Authentication: 인증정보 받아오기 위해 사용
        boardValidator.validate(board,bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        
        String username = authentication.getName();
        boardService.save(username, board);
//        boardRepository.save(board);
        return "redirect:/board/list";
    }
    

//    @DeleteMapping("board/{id}")
//    public void boardDelete(Model model, @PathVariable long id) {
//    	boardService.deleteBoard(id);
//    }
    
    
    @PostMapping("/deleteForm/{id}")
    public String delete(@PathVariable("id") String id) {
    	System.out.println("삭제한 게시글 : "+id);
//        boardService.deleteBoard(id);
    	boardRepository.deleteById(Long.parseLong(id));
        return "redirect:/board/list";
    }
    
    
}
