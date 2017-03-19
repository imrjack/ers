package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		this.doPost(req,resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("loggin out");
		HttpSession session = req.getSession(false);
		System.out.println("checking session 2 " + session);
		if(session != null){
			session.invalidate();
		}
		//why doesn't the below work?
		resp.sendRedirect(req.getContextPath()+"/login.jsp");
//		resp.sendRedirect("../login.jsp");
	}
}