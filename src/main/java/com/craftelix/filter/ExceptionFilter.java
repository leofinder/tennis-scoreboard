package com.craftelix.filter;

import com.craftelix.exception.InvalidParameterException;
import com.craftelix.exception.NotFoundException;
import com.craftelix.util.JspHelper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(value = "/*")
public class ExceptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (InvalidParameterException e) {
            log.error("HTTP Response Code: {}. {}", HttpServletResponse.SC_BAD_REQUEST, e.getMessage(), e);
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(JspHelper.getPath("400")).forward(req, resp);
        } catch (NotFoundException e) {
            log.error("HTTP Response Code: {}. {}", HttpServletResponse.SC_NOT_FOUND, e.getMessage(), e);
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            req.getRequestDispatcher(JspHelper.getPath("404")).forward(req, resp);
        } catch (RuntimeException e) {
            log.error("HTTP Response Code: {}. {}", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(), e);
            throw e;
        }
    }
}
