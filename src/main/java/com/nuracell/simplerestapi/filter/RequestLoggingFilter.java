package com.nuracell.simplerestapi.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class RequestLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        log.info("RequestID: {}, from: {}:{}, requestURL: {}",
                req.getRequestId(),
                req.getRemoteAddr(),
                req.getRemotePort(),
                req.getRequestURL());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
