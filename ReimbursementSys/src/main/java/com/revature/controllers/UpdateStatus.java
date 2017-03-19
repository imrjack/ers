package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.ers.DaoFacade;

public class UpdateStatus extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = (HttpSession) req.getSession(false);
		int resolver_id = (int) session.getAttribute("id");
		int status_type_id = Integer.parseInt(req.getParameter("status"));
		int reimbursement_id = Integer.parseInt(req.getParameter("reimb_id"));
		try(DaoFacade df = new DaoFacade() ){
			df.updateReimbursementStatus(reimbursement_id, resolver_id, status_type_id);
			System.out.println(req.getRequestDispatcher(req.getRequestURL() +"/all_reimbursements.do"));
			req.getRequestDispatcher("all_reimbursements.do?status=0").forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
