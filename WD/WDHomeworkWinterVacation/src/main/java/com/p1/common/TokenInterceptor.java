package com.p1.common;

import com.p1.exception.token.TokenBad;
import com.p1.exception.token.TokenNotFound;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截token
 * */
public class TokenInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws TokenNotFound,TokenBad {
        String token=request.getHeader("Authorization");
        if (token==null){       //token不为空
            throw new TokenNotFound();
        }else {
            if (TokenUnit.verify(token)){       //检查token
                return true;
            }
            throw new TokenBad(token);
        }
    }

}
