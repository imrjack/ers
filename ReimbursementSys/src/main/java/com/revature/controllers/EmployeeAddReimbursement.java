package com.revature.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.revature.constants.Const;
import com.revature.ers.DaoFacade;

/*
 * add reimbursement
 * using daofacade.addReimbursement
 * get amount, description, and type from client
 * get author_id from session
 * set status id to 1
 */
@MultipartConfig
public class EmployeeAddReimbursement extends HttpServlet{
	
	String fieldname;
	String fieldvalue;
	InputStream filecontent;
	Double amount;
	String description;
	int type_id;
	boolean hasFile = false;
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession(false);
//		Double amount = Double.parseDouble(req.getParameter("amount"));
//		String description = req.getParameter("description");
//		int type_id = Integer.parseInt(req.getParameter("type"));
		int author_id = (int) session.getAttribute("id");
		List<FileItem> items;
		try(DaoFacade df = new DaoFacade()) {
			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
			for (FileItem item : items) {
				if (item.isFormField()) {
					// Process regular form field (input type="text|radio|checkbox|etc", select, etc).
					fieldname = item.getFieldName();
					fieldvalue = item.getString();
					System.out.println("ahh:  " + fieldvalue);
					// ... (do your job here)
					switch(fieldname){
						case "amount": amount = Double.parseDouble(fieldvalue);
							break;
						case "type" : type_id=Integer.parseInt(fieldvalue);
							break;
						case "description":description = fieldvalue;
							break;
						default: break;
					}
				} else {
					// Process form file field (input type="file").
					fieldname = item.getFieldName();
					String filename = FilenameUtils.getName(item.getName());
					filecontent = item.getInputStream();
					hasFile= true;
				}
			}
			if(hasFile){
				df.addReimbursement(author_id, amount, description,Const.STATUS_PENDING, type_id, filecontent);
			}else{
				df.addReimbursement(author_id, amount, description,Const.STATUS_PENDING, type_id);	
			}
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			resp.sendRedirect("emp_reimbursements.do");
		}
	
//		try(DaoFacade df = new DaoFacade()){
//			if(req.getParameterMap().containsKey("receipt")){
////				String receipt = req.getParameter("receipt");
//				Part filePart = req.getPart("receipt");
//				System.out.println(filePart);
//				return;				//				df.addReimbursement(author_id, amount, description,Const.STATUS_PENDING, type_id,(Blob) receipt);			
//			}else{
////				df.addReimbursement(author_id, amount, description,Const.STATUS_PENDING, type_id);	
//			}
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			resp.sendRedirect("emp_reimbursements.do");
//		}
	}

}

