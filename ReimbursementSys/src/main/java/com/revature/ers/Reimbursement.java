package com.revature.ers;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;


public class Reimbursement {

	private int id;
	private Double amount;
	private Timestamp resolved;
	private String description;
	private Timestamp submitted;
	private InputStream receipt;
	private int authorId;
	private String authorName;
	private String resolverName;
	private int resolverId;
	private int statusId;
	private int typeId;
	private ReimbursementType type;
	private ReimbursementStatus status;
	
	

	public Reimbursement(int id, Double amount, Timestamp resolved, String description, Timestamp submitted, InputStream receipt,
			int authorId, int resolverId, ReimbursementType type, ReimbursementStatus status) {
		super();
		this.id = id;
		this.amount = amount;
		this.resolved = resolved;
		this.description = description;
		this.submitted = submitted;
		this.receipt = receipt;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.type = type;
		this.status = status;
	}

	public Reimbursement() {
		super();
	}
	public void setType(ReimbursementType type) {
		this.type = type;
	}
	
	public ReimbursementType getType() {
		return type;
	}
	
	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}
	public ReimbursementStatus getStatus(){
		return status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Timestamp getResolved() {
//		String simpleTimeStamp = new SimpleDateFormat("MM/dd/yyyy").format(resolved);
//		System.out.println(simpleTimeStamp);
		return resolved;
	}
	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}
	public InputStream getReceipt() {
		return receipt;
	}
	public void setReceipt(InputStream receipt) {
		this.receipt = receipt;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName(){
		return this.authorName;
	}
	public void setAuthorName(String authorName){
		this.authorName = authorName;
	}
	public String getResolverName(){
		return this.resolverName;
	}
	public void setResolverName(String resolverName){
		this.resolverName=resolverName;	
	}
	public int getResolverId() {
		return resolverId;
	}
	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", resolved=" + resolved + ", description="
				+ description + ", submitted=" + submitted + ", receipt=" + receipt + ", authorId=" + authorId
				+ ", resolverId=" + resolverId + ", statusId=" + statusId + ", typeId=" + typeId + ", status=" + status
				+ ", type=" + type + "]";
	}
	
	
	
	
}
