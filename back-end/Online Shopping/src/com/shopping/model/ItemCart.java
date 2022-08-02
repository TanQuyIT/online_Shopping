package com.shopping.model;

public class ItemCart {
	private Long id;
	private String name;
	private Long id_item;
	private Long id_cart;
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
	public Long getId_cart() {
		return id_cart;
	}
	public void setId_cart(Long id_cart) {
		this.id_cart = id_cart;
	}
	
	public ItemCart(Long id, String name, Long id_item, Long id_cart) {
		this.id = id;
		this.name = name;
		this.id_item = id_item;
		this.id_cart = id_cart;
	}
	
	
}
