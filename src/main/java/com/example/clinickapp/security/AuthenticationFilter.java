package com.example.clinickapp.security;

import com.example.clinickapp.entity.UserSession;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Component
@Order(1)
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, String[]> param = request.getParameterMap();
        String language = param.get("language") == null ? "uz" : param.get("language")[0];

        HttpSession httpSession = request.getSession();
        UserSession userSession = (UserSession) httpSession.getAttribute("user_session");
        if (null == userSession) {
            userSession = new UserSession();
            userSession.setSessionId(request.getSession().getId());
            userSession.setLanguage(language);
            userSession.setActiveSessionDate(new Date(httpSession.getCreationTime()));
            userSession.setRemoteAddress(request.getRemoteAddr());
            userSession.setOsInfo(request.getHeader("User-agent"));

            httpSession.setAttribute("user_session", userSession);
        }
        filterChain.doFilter(request, response);
    }
}
