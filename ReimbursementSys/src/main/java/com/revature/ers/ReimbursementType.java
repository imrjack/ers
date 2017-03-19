package com.revature.ers;

public class ReimbursementType {
	private int typeId;
	private String type;
	public ReimbursementType(int typeId, String type) {
		super();
		this.typeId = typeId;
		this.type = type;
	}
	public ReimbursementType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
