package com.shopping.model;

import java.util.Date;

public class Cart {
	private Long id;
	private Date date;
	private Long id_user;
	private Integer count;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Cart(Long id, Date date, Long id_user, Integer count) {
		this.id = id;
		this.date = date;
		this.id_user = id_user;
		this.count = count;
	}
	
	
} 
