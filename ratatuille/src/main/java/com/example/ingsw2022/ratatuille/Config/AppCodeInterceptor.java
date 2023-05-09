package com.example.ingsw2022.ratatuille.Config;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AppCodeInterceptor implements HandlerInterceptor {

    private static final String HEADER_APP_CODE = "app_code";
    private static final String EXPECTED_APP_CODE = "Ratatuille23";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String appCode = request.getHeader(HEADER_APP_CODE);
        if (appCode == null || !appCode.equals(EXPECTED_APP_CODE)) {
            System.out.println("Access denied");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        return true;
    }
    
    // Override degli altri metodi dell'interceptor se necessario
    
}
