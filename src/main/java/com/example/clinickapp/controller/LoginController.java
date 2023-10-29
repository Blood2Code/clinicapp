package com.example.clinickapp.controller;

import com.example.clinickapp.dto.JwtResponseDto;
import com.example.clinickapp.dto.LoginDto;
import com.example.clinickapp.dto.ResponseDto;
import com.example.clinickapp.dto.UserLoginDto;
import com.example.clinickapp.service.UserLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserLoginService userLoginService;

    @GetMapping("/")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, Model model, HttpServletRequest request) {
        ResponseDto<LoginDto> isValid = userLoginService.login(loginDto);
        model.addAttribute("data", isValid);
        HttpSession httpSession = request.getSession();
//        if (httpSession.)
        ;
        if (isValid.getCode().equals(0)) {
            return "patient";
        } else {
            return "redirect:/?error=true";
        }
    }
}
