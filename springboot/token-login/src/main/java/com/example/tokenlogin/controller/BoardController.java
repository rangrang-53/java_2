package com.example.tokenlogin.controller;

import com.example.tokenlogin.model.Article;
import com.example.tokenlogin.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String boardList() {
        return "board-list";
    }

    @GetMapping("/write")
    public String boardWrite() {
        return "board-write";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "board-detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")Long id, Model model) {
        model.addAttribute("id", id);
        return "board-update";
    }


}
