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
 * if user tries to access login page while logged in, 
 * redirect to home page
 */
public class AlreadyLoggedInFilter implements Filter{
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if(request.getSession().getAttribute("username") != null){
			HttpServletResponse response = (HttpServletResponse) resp;
			
			String role = (String) request.getSession().getAttribute("role");
			if(role.equals(Const.ROLE_MANAGER) ){
				response.sendRedirect(request.getServletContext().getContextPath() +"/all_reimbursements.do");
			}else if(role.equals(Const.ROLE_EMPLOYEE)){
				response.sendRedirect(request.getServletContext().getContextPath() +"/emp_reimbursements.do");
			}
		}else{
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
