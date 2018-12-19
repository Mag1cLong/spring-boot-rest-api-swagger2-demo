package com.example.demo.filter;

import com.example.demo.config.GlobalConfig;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Response过滤
 * Created by jcl on 2018/12/19
 */
@Component
@WebFilter(urlPatterns = "/*")
public class ResponseFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.addHeader("Access-Control-Expose-Headers", GlobalConfig.HEADERS_X_TOTAL_COUNT);//设置允许客户端获取的头信息
        response.addHeader("Access-Control-Allow-Origin", "*");//允许跨域,*代表所有
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
