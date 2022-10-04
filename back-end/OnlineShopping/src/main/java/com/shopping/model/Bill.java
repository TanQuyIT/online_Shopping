package com.shopping.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id")
	private int id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "buy_date")
	private Date buyDate;

	@Column(name = "price_total")
	private long priceTotal;

	@Column
	private String status;

	public Bill() {
		// TODO Auto-generated constructor stub
	}

	public Bill(int id, User user, Date buyDate, long priceTotal, String status) {
		super();
		this.id = id;
		this.user = user;
		this.buyDate = buyDate;
		this.priceTotal = priceTotal;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getuser() {
		return user;
	}

	public void setuser(User user) {
		this.user = user;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public long getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(long priceTotal) {
		this.priceTotal = priceTotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
