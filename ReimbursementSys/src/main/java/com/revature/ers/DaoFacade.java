package com.revature.ers;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class DaoFacade implements DaoInterface, AutoCloseable {
	private UserDao userDao;
	private ReimbursementDao reimbursementDao;
	private Connection conn;
	private User user = null;
	
	public DaoFacade(){
		try{
			conn = ConnectionFactory.getConnection();
			userDao = new UserDao(conn);
			reimbursementDao = new ReimbursementDao(conn);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public User getUser(String username) {
		try {
			user = userDao.getOne(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
			
	}

	@Override
	public List<User> getAllUsers() {
		List <User> users = null;
		try {
			users= userDao.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public boolean verifyUser(String username, String password) {
		boolean userExists= false;
		try {
			userExists = userDao.isUser(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userExists;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		List<Reimbursement> reimb = null;
		try {
			reimb = reimbursementDao.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimb;
	}

	@Override
	public List<Reimbursement> getAllReimbursements(int status) {
		List<Reimbursement> reimb = null;
		try {
			reimb = reimbursementDao.getAll(status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimb;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		if(conn != null)
			conn.close();
	}

	@Override
	public List<Reimbursement> getUserReimbursements(int user_id) {
		List <Reimbursement> reimb = null;
		try{
			reimb = reimbursementDao.getUserReimbursements(user_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return reimb;
	}
	@Override
	public List<Reimbursement> getUserReimbursements(int user_id, int status_id) {
		List <Reimbursement> reimb = null;
		try{
			reimb = reimbursementDao.getUserReimbursements(user_id, status_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return reimb;
	}
	
	@Override
	public void addReimbursement(int author_id, Double amount, String description, int status_id, int type_id) {
		try{
			reimbursementDao.addReimbursement(author_id, amount, description, status_id, type_id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void addReimbursement(int author_id, Double amount, String description, int status_id, int type_id, InputStream filecontent) {
		try{
			reimbursementDao.addReimbursement(author_id, amount, description, status_id, type_id, filecontent);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateReimbursementStatus(int reimbursement_id, int resolver_id, int status_type_id) {
		try{
			reimbursementDao.updateReimbursementStatus(reimbursement_id, resolver_id, status_type_id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
