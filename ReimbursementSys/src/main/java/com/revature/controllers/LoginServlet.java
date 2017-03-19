package com.revature.controllers;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.constants.Const;
import com.revature.ers.DaoFacade;
import com.revature.ers.User;

/*
 * Verify user and sets session if user exists
 * to do: create cookie
 */
public class LoginServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("in my doget for login");
//		req.setAttribute("please_login", "you must login");
		req.getRequestDispatcher("login.jsp").forward(req, resp);
//		resp.sendRedirect("login.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		System.out.println("in my post:" + req.getRequestURI());
		String username = req.getParameter("u_name");
		String password = req.getParameter("pass");
		DaoFacade df = new DaoFacade();
		boolean isUser = df.verifyUser(username, password);
		HttpSession session = req.getSession(true);
		if(isUser){			
			try{
				//create user object to store in session
				User user = df.getUser(username);
				session.setAttribute("username", user.getUsername());
				session.setAttribute("first_name", user.getFirstName());
				session.setAttribute("role", user.getRole().getRole());
				session.setAttribute("id", user.getUserId());
				//redirect to page determined by role
				if(session.getAttribute("role").equals(Const.ROLE_MANAGER)){
					System.out.println("verified is managaer");
//					resp.sendRedirect(req.getServletContext().getContextPath() + "/manager/home.do"); 					
					resp.sendRedirect("all_reimbursements.do");				
				}else if(session.getAttribute("role").equals(Const.ROLE_EMPLOYEE)){
//					System.out.println("is employee");
					resp.sendRedirect("emp_reimbursements.do");
				}
				
//				Enumeration<String> e = session.getAttributeNames();
//				while (e.hasMoreElements()) {
//					String name = (String) e.nextElement();
//					System.out.println(name + ": " + session.getAttribute(name) );
//				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			req.setAttribute("please_login", "incorrect username or password");
//			resp.sendRedirect("login.jsp");
			req.getRequestDispatcher("login.jsp").forward(req,resp);

		}
		try {
			df.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

