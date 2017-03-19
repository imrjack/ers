package com.revature.ers;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.util.Time;


public class ReimbursementDao {

	private Connection conn;
	
	public ReimbursementDao(Connection conn){
		this.conn = conn;
	}
	public List<Reimbursement> getUserReimbursements(int user_id) throws Exception{
		String sql = "SELECT * FROM user_reimbursements_view "
					+ "WHERE reimb_author= ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, user_id);
		ResultSet rs = stmt.executeQuery();
		
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		while(rs.next()){
			Reimbursement reimb = new Reimbursement();
			reimb.setStatus(new ReimbursementStatus(rs.getInt("REIMB_STATUS_ID"),rs.getString("REIMB_STATUS")));
			reimb.setType(new ReimbursementType(rs.getInt("REIMB_TYPE_ID"),rs.getString("REIMB_TYPE")));
			reimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
			reimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
			reimb.setResolved(rs.getTimestamp("REIMB_RESOLVED"));
			reimb.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
			reimb.setResolverName(rs.getString("RESOLVER_NAME"));
			list.add(reimb);					
		}
		return list;
	}
	public List<Reimbursement> getUserReimbursements(int user_id, int status_id) throws Exception{
		String sql = "SELECT * FROM user_reimbursements_view "
					+ "WHERE reimb_author= ? and reimb_status_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, user_id);
		stmt.setInt(2, status_id);
		ResultSet rs = stmt.executeQuery();
		
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		while(rs.next()){
			Reimbursement reimb = new Reimbursement();
			reimb.setStatus(new ReimbursementStatus(rs.getInt("REIMB_STATUS_ID"),rs.getString("REIMB_STATUS")));
			reimb.setType(new ReimbursementType(rs.getInt("REIMB_TYPE_ID"),rs.getString("REIMB_TYPE")));
			reimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
			reimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
			reimb.setResolved(rs.getTimestamp("REIMB_RESOLVED"));
			reimb.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
			reimb.setResolverName(rs.getString("RESOLVER_NAME"));
			
			Blob imageBlob = rs.getBlob("REIMB_RECIEPT");
			InputStream binaryStream = imageBlob.getBinaryStream();
			reimb.setReceipt(binaryStream);
			list.add(reimb);					
		}
		return list;
	}	
	
	public List<Reimbursement> getAll() throws Exception{
		String sql ="SELECT * FROM get_all_reimbursements";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<Reimbursement> list = new ArrayList<Reimbursement>();
		while(rs.next()){
			Reimbursement reimb = new Reimbursement();	
			reimb.setId(rs.getInt("REIMB_ID"));
			reimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
			reimb.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
			reimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
			reimb.setAuthorName(rs.getString("AUTHOR_NAME"));
			reimb.setResolverName(rs.getString("RESOLVER_NAME"));
			reimb.setStatus(new ReimbursementStatus(rs.getInt("REIMB_STATUS_ID"),rs.getString("REIMB_STATUS")));
			reimb.setType(new ReimbursementType(rs.getInt("REIMB_TYPE_ID"),rs.getString("REIMB_TYPE")));
			Blob imageBlob = rs.getBlob("REIMB_RECIEPT");
			InputStream binaryStream = imageBlob.getBinaryStream(0, imageBlob.length());
			reimb.setReceipt(binaryStream);
			list.add(reimb);
		}
		return list;
	}
	public List<Reimbursement> getAll(int status) throws Exception{
		String sql = "SELECT * FROM get_all_reimbursements r "
					+ "WHERE r.REIMB_STATUS_ID = ? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, status);
		ResultSet rs = stmt.executeQuery();
		
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		while(rs.next()){
			Reimbursement reimb = new Reimbursement();	
			reimb.setId(rs.getInt("REIMB_ID"));
			reimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
			reimb.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
			reimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
			reimb.setAuthorName(rs.getString("AUTHOR_NAME"));
			reimb.setResolverName(rs.getString("RESOLVER_NAME"));
			reimb.setStatus(new ReimbursementStatus(rs.getInt("REIMB_STATUS_ID"),rs.getString("REIMB_STATUS")));
			reimb.setType(new ReimbursementType(rs.getInt("REIMB_TYPE_ID"),rs.getString("REIMB_TYPE")));
			Blob imageBlob = rs.getBlob("REIMB_RECIEPT");
			InputStream binaryStream = imageBlob.getBinaryStream(0, imageBlob.length());
			reimb.setReceipt(binaryStream);
			list.add(reimb);
		}
		return list;
	}
	
	public void addReimbursement(int author_id
			, Double amount
			, String description
			, int status_id
			, int type_id) throws Exception{
		Timestamp time = new Time().currentTime();		
		String sql = "INSERT INTO ERS_REIMBURSEMENT(REIMB_AUTHOR,REIMB_AMOUNT,REIMB_SUBMITTED,REIMB_DESCRIPTION,REIMB_STATUS_ID,REIMB_TYPE_ID) "
				 	+ "VALUES(?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, author_id);
		stmt.setDouble(2, amount);
		stmt.setTimestamp(3, time);
		stmt.setString(4,description);
		stmt.setInt(5, status_id);
		stmt.setInt(6, type_id);
		stmt.executeUpdate();
	}

	
	//Upload with blob
	public void addReimbursement(int author_id
					, Double amount
					, String description
					, int status_id
					, int type_id
					, InputStream filecontent) throws Exception{
		Timestamp time = new Time().currentTime();		
		String sql = "INSERT INTO ERS_REIMBURSEMENT(REIMB_AUTHOR,REIMB_AMOUNT,REIMB_SUBMITTED,REIMB_DESCRIPTION,REIMB_STATUS_ID,REIMB_TYPE_ID, REIMB_RECIEPT) "
				 	+ "VALUES(?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.println(filecontent);
		stmt.setInt(1, author_id);
		stmt.setDouble(2, amount);
		stmt.setTimestamp(3, time);
		stmt.setString(4,description);
		stmt.setInt(5, status_id);
		stmt.setInt(6, type_id);
		stmt.setBlob(7,filecontent);
		stmt.executeUpdate();
	}
	
	public void updateReimbursementStatus(int reimbursement_id, int resolver_id, int status_type_id) throws SQLException{
		Timestamp resolved_time = new Time().currentTime();
		String sql = "UPDATE ers_reimbursement "
					+"SET reimb_resolver = ?"
					+", reimb_status_id = ?"
					+", reimb_resolved = ? "
					+"WHERE reimb_id = ? ";
		PreparedStatement stmt= conn.prepareStatement(sql);
		stmt.setInt(1, resolver_id);
		stmt.setInt(2, status_type_id);
		stmt.setTimestamp(3, resolved_time);
		stmt.setInt(4,reimbursement_id);
		stmt.executeUpdate();
	}
		
//	
	public static void main(String[] args) throws Exception{
		//		List<Reimbursement> r = new ArrayList<Reimbursement>();
//		String str="APPROVED";
//		r = new ReimbursementDao(ConnectionFactory.getConnection()).getAll(str);
//		System.out.println('1');
//		for( Reimbursement k : r){
//			System.out.println(k.getType().getType());
//			System.out.println("hi");
//		}
		//Timestamp time = new Time().currentTime();
//		List<Reimbursement> list = new ReimbursementDao(ConnectionFactory.getConnection()).getAll();
//		for(Reimbursement l : list){
//			System.out.println(l.getAuthorName());
//		}
		//DaoFacade rd = new DaoFacade();
		//rd.addReimbursement(2, 000, "description with dsao", 3, 2);
		System.out.println("done");
//		List<Reimbursement> list = new ReimbursementDao(ConnectionFactory.getConnection()).getUserReimbursement(2);
//		for(Reimbursement l : list){
//			System.out.println(l.getResolverName());
//		}
		//new ReimbursementDao(ConnectionFactory.getConnection()).updateReimbursementStatus(5, 1, 3);
	}
}
