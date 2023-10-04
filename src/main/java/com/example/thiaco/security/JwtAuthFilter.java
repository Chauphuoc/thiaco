package com.example.thiaco.security;

import com.example.thiaco.service.JwtService;
import com.example.thiaco.service.UserPrincipleService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserPrincipleService userPrincipleService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String bearerToken = getBearerTokenRequest(request);
            String authorizationCookie = getCookieValue(request);
            setAuthentication(request, authorizationCookie);
            setAuthentication(request, bearerToken);

        } catch (Exception e) {
            logger.error("Can NOT set user authentication -> Message: {0}", e);
        }
        filterChain.doFilter(request, response);
    }

    private String getBearerTokenRequest(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }

    private String getCookieValue(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
//                check khi gửi lên ở cookie có tên là JWT không,
//                nên khi user đăng nhập cần lưu đúng tên cookie JWT với value là mã token
                if (cookie.getName().equals("JWT")){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void setAuthentication(HttpServletRequest request, String tokenValue){
//        set Authentication cho tài khoản gửi request có mã token hợp lệ
        if (tokenValue != null && jwtService.validateJwtToken(tokenValue)){
            String username = jwtService.getUserNameFromJwtToken(tokenValue);
            System.out.println(username);
            UserDetails userDetail = userPrincipleService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
}