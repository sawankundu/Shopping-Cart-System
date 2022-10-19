package com.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "db_sequence")
@Data
public class DbSequence {
	
	@Id
	private String id;
	private int seq;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	@Override
	public String toString() {
		return "DbSequence [id=" + id + ", seq=" + seq + ", getId()=" + getId() + ", getSeq()=" + getSeq()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	public DbSequence(String id, int seq) {
		super();
		this.id = id;
		this.seq = seq;
	}
	
	
	
	
}
