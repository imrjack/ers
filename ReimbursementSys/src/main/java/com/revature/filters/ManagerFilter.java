package com.revature.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.constants.Const;

/*
 * if manager restrict access to employee pages
 */
public class ManagerFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		if(session.getAttribute("role").equals(Const.ROLE_MANAGER)){
			System.out.println(session.getAttribute("username"));
			System.out.println("in manager filter");
			HttpServletResponse response = (HttpServletResponse) resp;
			req.getRequestDispatcher("../errors/401.html").forward(req,resp);
		}else{
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
