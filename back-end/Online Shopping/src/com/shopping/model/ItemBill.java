package com.shopping.model;

public class ItemBill {
	private Long id;
	private String name;
	private Long id_item;
	private long id_bill;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId_item() {
		return id_item;
	}
	public void setId_item(Long id_item) {
		this.id_item = id_item;
	}
	public long getId_bill() {
		return id_bill;
	}
	public void setId_bill(long id_bill) {
		this.id_bill = id_bill;
	}
	
	public ItemBill(Long id, String name, Long id_item, long id_bill) {
		this.id = id;
		this.name = name;
		this.id_item = id_item;
		this.id_bill = id_bill;
	}
	
	
}
