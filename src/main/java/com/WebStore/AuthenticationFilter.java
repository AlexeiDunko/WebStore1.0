package com.WebStore;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class AuthenticationFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        // if session does not exist or user did not agree with terms, redirect to error page
        if (session == null || session.getAttribute("agreed") == null) {
            response.sendRedirect("error.jsp");
            return;
        }
        chain.doFilter(req, res);
    }
}
