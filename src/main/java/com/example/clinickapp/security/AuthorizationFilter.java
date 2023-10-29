package com.example.clinickapp.security;

import com.example.clinickapp.entity.UserSession;
import com.example.clinickapp.repository.UserLoginRepository;
import com.example.clinickapp.service.mapper.UserLoginMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Order(2)
public class AuthorizationFilter extends OncePerRequestFilter {
    private final UserLoginRepository userLoginRepository;
    private final UserLoginMapper userLoginMapper;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        Map<String, String[]> param = request.getParameterMap();

        HttpSession httpSession = request.getSession();
        UserSession userSession = (UserSession) httpSession.getAttribute("user_session");
        if (null != userSession) {

            filterChain.doFilter(request, response);
        }
    }
}
