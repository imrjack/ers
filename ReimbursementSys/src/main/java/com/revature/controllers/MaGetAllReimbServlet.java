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
 * if status param exists get reimbursement with status id type
 * else get all reimbursements
 */
public class MaGetAllReimbServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<Reimbursement> reimbursements_list = null;

		//check if status get param exists

		try(DaoFacade df = new DaoFacade()){			
			if(req.getParameterMap().containsKey("status")){
				int status_id =Integer.parseInt(req.getParameter("status"));			
				if(status_id > 0)reimbursements_list = df.getAllReimbursements(status_id);
				else if(status_id == 0) reimbursements_list = df.getAllReimbursements();
			}else{
				reimbursements_list = df.getAllReimbursements(Const.STATUS_PENDING);
			}
			req.setAttribute("all_reimbursements", reimbursements_list);				
			req.getRequestDispatcher("/manager/home.jsp").forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
		}
			//	Enumeration<String> e = session.getAttributeNames();
//			while (e.hasMoreElements()) {
//				String name = (String) e.nextElement();
//				System.out.println(name + ": " + session.getAttribute(name) );
//			}
			
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		System.out.println("in my post ");
		this.doGet(req, resp);

	}
}
