package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.constants.Const;
import com.revature.ers.DaoFacade;
import com.revature.ers.Reimbursement;
/*
 * 		if status param exists, get user reimbursement with status else get all
 */
public class EmployeeReimbServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp )throws ServletException, IOException{
		System.out.println("in employee servlet gett");
		List<Reimbursement> emp_reimb_list = null;
		HttpSession session = req.getSession(false);

		try(DaoFacade df = new DaoFacade()){
			int id = (int) session.getAttribute("id");
			
			if(req.getParameterMap().containsKey("status")){
				int status_id = Integer.parseInt(req.getParameter("status"));
				if(status_id > 0)emp_reimb_list = df.getUserReimbursements(id, status_id);
				else if(status_id == 0) emp_reimb_list = df.getUserReimbursements(id); 
			}else{
				emp_reimb_list = df.getUserReimbursements(id, Const.STATUS_PENDING);
			}
			req.setAttribute("employee_reimbursement", emp_reimb_list);

			req.getRequestDispatcher("/employee/home.jsp").forward(req,resp);
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp )throws ServletException, IOException{
		
	}	
}
