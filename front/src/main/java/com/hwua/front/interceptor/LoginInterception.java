package com.hwua.front.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Component
public class LoginInterception implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            System.out.println("------------没有登陆------------");
            String requestURI = request.getRequestURI();
            System.out.println("***************requestURI = " + requestURI);

            String servletPath = request.getServletPath();
            System.out.println("servletPath = " + servletPath);
            Map<String, String[]> parameterMap = request.getParameterMap();

            Set<String> strings = parameterMap.keySet();
            for (String s : strings) {
                System.out.println("key  = " + s + "---- value= " + Arrays.toString(parameterMap.get(s)));
            }
            response.sendRedirect(request.getContextPath()+ "/login?URI="+servletPath);
            return false;
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
