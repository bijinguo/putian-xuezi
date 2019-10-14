package com.example.putian.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GlobalInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object uid = session.getAttribute("uid");
        if (uid==null){




            response.sendRedirect("http://localhost:8080/web/login.html");
            return  false;
        }


        return true;
    }
}
