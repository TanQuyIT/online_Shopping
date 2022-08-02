package com.shopping.model;

import java.util.Date;

public class Review {
	private Long id;
	private Long id_user;
	private Date date;
	private Long id_item;
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getId_item() {
		return id_item;
	}
	public void setId_item(Long id_item) {
		this.id_item = id_item;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Review(Long id, Long id_user, Date date, Long id_item, String content) {
		this.id = id;
		this.id_user = id_user;
		this.date = date;
		this.id_item = id_item;
		this.content = content;
	}
	
	
}
