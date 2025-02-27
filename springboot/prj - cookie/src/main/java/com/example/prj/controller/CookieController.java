package com.example.prj.controller;

import com.example.prj.dto.UserDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CookieController {

    private List<UserDTO> users = new ArrayList<>();

    @GetMapping("/main")
    public String main(
            HttpServletRequest request,
            Model model
    ) {
        Cookie[] cookies = request.getCookies();
        String id = null;

        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("id")) {
                    id = cookie.getValue();
                    break;
                }
            }
        }

        if(id != null) {
            model.addAttribute("id", id);
        } else {
            model.addAttribute("message", "쿠키가 존재하지 않습니다.");
        }
        return "main";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginExc(
            @RequestParam String id,
            @RequestParam String password,
            HttpServletResponse response,
            Model model){
        Cookie cookie = new Cookie("id",id);
        cookie.setMaxAge(7*24*60*60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        for (UserDTO user : users) {
            if(user.getId() != null && user.getId().equals(id) && user.getPassword().equals(password)) {

                cookie.setAttribute("id", user.getId());


                return "redirect:main";
            }
        }
        model.addAttribute("message","비밀번호와 아이디가 일치하지 않습니다.");

        return "login";
    }

    @GetMapping("/logout")
    public String logoutExc(HttpServletResponse response) {

        Cookie cookie = new Cookie("id","");
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);

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
