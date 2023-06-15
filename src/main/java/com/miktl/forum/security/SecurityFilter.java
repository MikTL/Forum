package com.miktl.forum.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private TokenService tokenService;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
            throws ServletException, IOException
    {
        String tokenAuth = request.getHeader("Authorization");
        if(tokenAuth==null || tokenAuth ==""){
            throw new RuntimeException("El token no puede ser nulo");
        }
        tokenAuth=tokenAuth.replace("Bearer ", "");
        System.out.println(tokenAuth);
        System.out.println(tokenService.getSubject(tokenAuth));
        filterChain.doFilter(request,response);
    }
}
