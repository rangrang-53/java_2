package com.example.prj.controller;

import com.example.prj.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SessionController {

    private List<UserDTO> users = new ArrayList<>();

    @GetMapping("/main")
    public String main(HttpSession session,
                       Model model) {
        String id = (String) session.getAttribute("id");

        if(id != null) {
            model.addAttribute("id", id);
        }
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginExc(
            @RequestParam String id,
            @RequestParam String password,
            HttpSession session,
            Model model){
        for (UserDTO user : users) {
            if(user.getId() != null && user.getId().equals(id) && user.getPassword().equals(password)) {

                session.setAttribute("id", user.getId());


                return "redirect:main";
            }
        }
        model.addAttribute("message","비밀번호와 아이디가 일치하지 않습니다.");

        return "login";
    }

    @GetMapping("/logout")
    public String logoutExc(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signupExc(
            @RequestParam String id,
            @RequestParam String password,
            @RequestParam String nickname,
            Model model
    ){
        for (UserDTO user : users) {

            if(user.getId()!=null && user.getId().equals(id)){
                model.addAttribute("message","이미 존재하는 ID입니다.");
                return "signup";
            }
        }

        UserDTO user = new UserDTO();
        user.setId(id);
        user.setPassword(password);
        user.setNickname(nickname);
        users.add(user);

        return "redirect:login";

    }



}
