-- begin
-- 	execute immediate 'drop table "user"';
-- exception
-- 	when others then null;
-- end;

CREATE TABLE "user"(
	id number NOT NULL,
	username varchar2(20) NOT NULL,
	password varchar2(16) NOT NULL,
	fullName varchar2(30) NOT NULL,
	email varchar2(30) NOT NULL,
	phoneNumber nchar(10) NOT NULL,
	address varchar2(255) NOT NULL,
	type number NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE "shop"(
	id number NOT NULL,
	name varchar2(100) NOT NULL,
	address varchar2(50) NOT NULL,
	phoneNumber nchar(10) NOT NULL,
	describe varchar2(255) NOT NULL,
	img varchar2(255) NOT NULL,
	PRIMARY KEY (id)	
)

CREATE TABLE "item"(
	id number NOT NULL,
	name varchar2(100) NOT NULL,
	price number NOT NULL,
	saled number NOT NULL,
	describe varchar2(255) NOT NULL,
	img varchar2(255) NOT NULL,
	img varchar2(255) NOT NULL,
	releaseDate Date NOT NULL,
	id_category number NOT NULL,
	id_producer number NOT NULL,
	id_shop number NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (id_category) REFERENCES category (id),
	FOREIGN KEY (id_producer) REFERENCES producer (id),
	FOREIGN KEY (id_shop) REFERENCES shop (id)
)

CREATE TABLE "category"(
	id number NOT NULL,
	name varchar2(30) NOT NULL,
	PRIMARY KEY (id)
)

CREATE TABLE "producer"(
	id number NOT NULL,
	name varchar2(30) NOT NULL,
	PRIMARY KEY (id)
)

CREATE TABLE "cart"(
	id number NOT NULL,
	date Date NOT NULL,
	id_user number,
	count number,
	PRIMARY KEY (id),
	FOREIGN KEY id_user REFERENCES user (id)
)

CREATE TABLE "item_cart"(
	id number NOT NULL,
	name varchar2(100) NOT NULL,
	id_item number NOT NULL,
	id_cart number NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (id_item) REFERENCES item (id),
	FOREIGN KEY (id_cart) REFERENCES cart (id)
)

CREATE TABLE "review"(
	id number NOT NULL,
	id_user number NOT NULL,
	date Date NOT NULL,
	id_item number NOT NULL,
	content varchar2(255) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY id_user REFERENCES user (id),
	FOREIGN KEY id_item REFERENCES item (id)
)

CREATE TABLE "bill"(
	id number NOT NULL,
	name varchar2(30) NOT NULL,
	id_user number NOT NULL,
	address varchar2(255) NOT NULL,
	phoneNumber nchar(10) NOT NULL,
	total number NOT NULL,
	date Date NOT NULL,
	status number NOT NULL,
	PRIMARY KEY (id), 
	FOREIGN KEY (id_user) REFERENCES user (id)
)

CREATE TABLE "item_bill"(
	id number NOT NULL,
	name varchar2(100) NOT NULL,
	id_item number NOT NULL,
	id_bill number NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (id_item) REFERENCES item (id),
	FOREIGN KEY (id_bill) REFERENCES bill (id)
)