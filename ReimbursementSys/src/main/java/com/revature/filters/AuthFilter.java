package com.revature.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/*
 * if not logged in, go back to login page
 */
public class AuthFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if(request.getSession().getAttribute("username") == null){
			req.setAttribute("please_login", "you must login");
			req.getRequestDispatcher("/login.do").forward(req, resp);
			System.out.println(request.getContextPath()+"/login.jsp 1"); // why doesn't this work?
//			//this caused inability to login
//			req.getRequestDispatcher("/login.jsp").forward(req, resp); 
		}else{
			chain.doFilter(req,resp);
		}
	}
//pageContext.request.contextPath
	@Override
	public void init(FilterConfig config) throws ServletException {
		
		
	}

}
