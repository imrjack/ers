package com.revature.ers;

import java.io.InputStream;
import java.util.List;


public interface DaoInterface {
	//USER METHODS
	public User getUser(String username);
	public List<User> getAllUsers();
	public boolean verifyUser(String username, String password);

	//REIMBURSEMENT METHODS
	public List <Reimbursement> getAllReimbursements();
	public List <Reimbursement> getAllReimbursements(int status);
	public List <Reimbursement> getUserReimbursements(int user_id);
	public List <Reimbursement> getUserReimbursements(int user_id, int status_id);	
	public void addReimbursement(int author_id, Double amount, String description, int status_id, int type_id, InputStream receipt);
	public void addReimbursement(int author_id, Double amount, String description, int status_id, int type_id);
	public void updateReimbursementStatus(int reimbursement_id, int resolver_id, int status_type_id);
	
}
