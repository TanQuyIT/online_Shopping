package com.shopping.model;

import java.util.Date;

public class Item {
	private Long id;
	private String name;
	private Long price;
	private Integer saled;
	private String describe;
	private String img;
	private Date releaseDate;
	private Long id_category;
	private Long id_producer;
	private Long id_shop;
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
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getSaled() {
		return saled;
	}
	public void setSaled(Integer saled) {
		this.saled = saled;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Long getId_category() {
		return id_category;
	}
	public void setId_category(Long id_category) {
		this.id_category = id_category;
	}
	public Long getId_producer() {
		return id_producer;
	}
	public void setId_producer(Long id_producer) {
		this.id_producer = id_producer;
	}
	public Long getId_shop() {
		return id_shop;
	}
	public void setId_shop(Long id_shop) {
		this.id_shop = id_shop;
	}
	
	public Item(Long id, String name, Long price, Integer saled, String describe, String img, Date releaseDate,
			Long id_category, Long id_producer, Long id_shop) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.saled = saled;
		this.describe = describe;
		this.img = img;
		this.releaseDate = releaseDate;
		this.id_category = id_category;
		this.id_producer = id_producer;
		this.id_shop = id_shop;
	}
	
	
}
