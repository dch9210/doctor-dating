package com.dch.service.dating.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public OriginInteceptor getOriginInterceptor() {
        return new OriginInteceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getOriginInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    class OriginInteceptor implements HandlerInterceptor {

        @Override
        public void afterCompletion(HttpServletRequest arg0,
                                    HttpServletResponse arg1, Object arg2, Exception arg3)
                throws Exception {

        }

        @Override
        public void postHandle(HttpServletRequest arg0,
                               HttpServletResponse arg1, Object arg2, ModelAndView arg3)
                throws Exception {

        }

        @Override
        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response, Object obj) throws Exception {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods",
                    "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers",
                    "Content-Type,Access-Token");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Expose-Headers", "*");
            return true;
        }

    }
}
