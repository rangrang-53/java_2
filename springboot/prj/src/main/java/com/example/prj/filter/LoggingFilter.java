package com.example.prj.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        if(request.getRequestURI().equals("/main")){System.out.println("Request URI : " + request.getRequestURI());
            System.out.println("Request Method : " + request.getMethod());}



        filterChain.doFilter(servletRequest, servletResponse);

        if(request.getRequestURI().equals("/main")){
            System.out.println("Response status : " + response.getStatus());
        }



    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
