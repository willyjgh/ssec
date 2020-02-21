package com.springsec.ssec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebMvc
public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {

    private static final String DEFAULT_LAYOUT = "shared/layout";
    private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        if (modelAndView == null || !modelAndView.hasView()) {
            return;
        }

        String originalViewName = modelAndView.getViewName();

        if (originalViewName.startsWith("redirect:") || originalViewName.startsWith("forward:")) {
            return;
        }

        if (request.getRequestURI().startsWith("/login")
                || request.getRequestURI().startsWith("/register")
                || request.getRequestURI().startsWith("/error403")
                || request.getRequestURI().startsWith("/error404")
                || request.getRequestURI().startsWith("/error500")
                || request.getRequestURI().startsWith("/public/api")
        ) {
            return;
        } else {
            modelAndView.setViewName(DEFAULT_LAYOUT);
        }

        modelAndView.addObject(DEFAULT_VIEW_ATTRIBUTE_NAME, originalViewName);
    }
}
