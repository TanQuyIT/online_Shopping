
DROP TABLE IF EXISTS category;

CREATE TABLE category(
    category_id number(19) NOT NULL primary key,
    category_name varchar(255) NOT NULL
)

INSERT INTO category VALUES (1,'Vans authentic'),
                            (2,'Vans classic'),
                            (3,'Vans ERA'),
                            (4,'Vans old skool'),
                            (5,'Vans SK8'),
                            (6,'Vans slip-on');

DROP TABLE IF EXISTS item;

CREATE TABLE item(
    item_id number(50) NOT NULL AUTO_INCREMENT,
    product_id number(50) NOT NULL,
    unit_price binary_double(20) NOT NULL,
    quantity number(20) NOT NULL,
    order_id number(50) NOT NULL,
    primary key (item_id), 
    foreign key product_id references product (product_id)
)

INSERT INTO item VALUES (9,51,77,1,14),
                        (10,88,50,1,14),
                        (11,49,52,2,15),
                        (12,50,54,1,15),
                        (13,88,50,3,15),
                        (14,89,48,1,15),
                        (15,51,77,3,16),
                        (16,47,60,1,16),
                        (17,49,52,2,17),
                        (18,58,59,1,17),
                        (19,49,52,2,21),
                        (20,60,68,1,22),
                        (21,50,54,1,23),
                        (22,88,49,3,23),
                        (23,89,48,2,23),
                        (24,51,76,2,24),
                        (25,52,64,1,24),
                        (26,58,59,5,25),
                        (27,47,60,3,25),
                        (28,50,54,1,26),
                        (29,89,48,2,26),
                        (30,64,71,1,27),
                        (31,51,76,4,27),
                        (32,77,53,1,27),
                        (33,51,76,1,28),
                        (34,47,60,3,28),
                        (35,52,64,1,29),
                        (36,47,60,1,30),
                        (37,81,42,2,31),
                        (38,60,68,1,31),
                        (39,81,42,3,32),
                        (40,98,42,1,33),
                        (41,77,53,1,33),
                        (42,49,52,3,34),
                        (43,95,44,2,34),
                        (44,98,70,5,35),
                        (45,55,67,2,35),
                        (46,72,70,3,35),
                        (47,47,60,2,36),
                        (48,51,76,1,37),
                        (49,51,76,1,38),
                        (50,60,68,1,39);


DROP TABLE IF EXISTS order_user;

CREATE TABLE order_user(
    order_id number(50) NOT NULL AUTO_INCREMENT,
    buy_date datetime NOT NULL,
    status varchar(100) NOT NULL,
    price_total binary_double NOT NULL,
    user_id number(50) NOT NULL,
    primary key (order_id, user_id),
    foreign key user_id references user (user_id)
)

INSERT INTO TABLE order_user VALUES (14,'2022-09-27 00:00:00','SUCCESS',132,40),
                                    (15,'2022-09-27 00:00:00','SUCCESS',361,70),
                                    (16,'2022-09-29 00:00:00','SUCCESS',296,40),
                                    (17,'2022-09-30 00:00:00','SUCCESS',168,40),
                                    (21,'2022-09-30 00:00:00','SUCCESS',109,40),
                                    (22,'2022-09-30 00:00:00','SUCCESS',73,70),
                                    (23,'2022-10-02 00:00:00','SUCCESS',302,40),
                                    (24,'2022-10-02 00:00:00','SUCCESS',221,76),
                                    (25,'2022-10-02 00:00:00','SUCCESS',480,76),
                                    (26,'2022-10-02 00:00:00','SUCCESS',155,78),
                                    (27,'2022-10-02 00:00:00','SUCCESS',433,78),
                                    (28,'2022-10-02 00:00:00','SUCCESS',261,78),
                                    (29,'2022-10-02 00:00:00','SUCCESS',69,78),
                                    (30,'2022-10-02 00:00:00','SUCCESS',65,78),
                                    (31,'2022-10-02 00:00:00','SUCCESS',157,79),
                                    (32,'2022-10-02 00:00:00','SUCCESS',131,81),
                                    (33,'2022-10-02 00:00:00','SUCCESS',100,81),
                                    (34,'2022-10-03 00:00:00','SUCCESS',249,40),
                                    (35,'2022-10-03 00:00:00','SUCCESS',699,95),
                                    (36,'2022-10-03 00:00:00','SUCCESS',125,95),
                                    (37,'2022-10-03 00:00:00','PENDING',81,76),
                                    (38,'2022-10-03 00:00:00','PENDING',81,70),
                                    (39,'2022-10-03 00:00:00','PENDING',73,40); 


DROP TABLE if EXISTS product;

CREATE TABLE product(
    product_id number(50) NOT NULL AUTO_INCREMENT,
    description varchar(255),
    image varchar(255),
    price binary_double NOT NULL,
    product_name varchar(255),
    quantity number(50) NOT NULL,
    category_id number(50) NOT NULL,
    sale_id number(50) NOT NULL,
    primary key (product_id),
    foreign key (category_id) references category (category_id),
    foreign key (sale_id)) references sale (sale_id),
)

INSERT INTO product VALUES (44,'Vans Authentic 44 Dx Classic White - Anaheim Factory','1611853378099.jpg',65,'Vans authentic',150,1,'s20pc'),
                           (47,'Vans Dallas Clayton Authentic Rainbow True -  White','1608622024114.jpg',67,'Vans authentic',55,1,'s10pc'),
                           (48,'Vans Sk8 - Hi Label Mix','1608622139500.jpg',60,'Vans old skool',152,4,'default'),
                           (49,'Sneakers Vans Authentic Black White','1609034076692.jpg',65,'Vans old skool',150,4,'s20pc'),
                           (50,'Vans old skool classic black/white','1608622277960.jpg',60,'Vans old skool',199,4,'s10pc'),
                           (51,'Vans Sk8-Hi Deck Club','1608622647893.jpg',85,'Vans SK8',120,5,'s10pc'),
                           (52,'Vans Sk8 Hi Black White','1608622710798.png',80,'Vans SK8',345,5,'s20pc'),
                           (53,'Vans Sk8 Hi Navy White\r\n','1608622786673.jpg',78,'Vans SK8',167,5,'default'),
                           (54,'Vans Sk8 Hi Navy White\r\n','1608622841439.png',89,'Vans SK8',313,5,'s30pc'),
                           (55,'Vans Old Skool Flash Skulls','1608622964916.jpg',67,'Vans old skool',467,4,'default'),
                           (56,'Vans Style 36 Crew Checkerboard','1608623182252.jpg',78,'Vans old skool',276,4,'s10pc'),
                           (57,'Vans Old Skool 36 DX Anaheim Factory','1612540190989.jpg',65,'Vans old skool',198,4,'s20pc'),
                           (58,'Vans Authentic Red White\r\n','1608623362414.png',59,'Vans authentic',435,1,'default'),
                           (59,'Vans Sk8-Hi Flame Reissue','1608623531124.jpg',70,'Vans SK8',365,5,'s30pc'),
                           (60,' Vans Sk8-Hi DIY Tapered White','1608623614207.png',85,'Vans SK8',423,5,'s20pc'),
                           (61,'Vans Sk8-Hi DIY Tapered Black','1608623670293.jpg',59,'Vans old skool',531,4,'s10pc'),
                           (62,'Vans Sk8-Hi Flame Reissue','1608623745484.jpg',79,'Vans SK8',484,5,'default'),
                           (63,'\r\nGiày Vans SK8-Hi Alien Ghosts','1608623803283.png',79,'Vans SK8',567,5,'s30pc'),
                           (64,'\r\nGiày Vans SK8 - Hi 38 DX Anaheim Factory','1608623863531.jpg',89,'Vans SK8',462,5,'s20pc'),
                           (65,'Vans SK8 - Hi 38 DX Anaheim Factory','1608624027198.jpg',69,'Vans SK8',352,5,'s10pc'),
                           (66,'Vans Sk8-Hi DIY Tapered White','1612268137270.jpg',69,'Vans old skool',535,4,'s40pc'),
                           (67,'Vans Old Skool Off The Wall Sidewall True Navy','1608624884611.png',59,'Vans old skool',215,4,'s30pc'),
                           (68,'Vans Authentic Black White','1608624944384.jpg',65,'Vans old skool',453,4,'s20pc'),
                           (69,'Vans Authentic 44 Dx Checkerbroad','1608625004288.jpg',69,'Vans old skool',379,4,'s40pc'),
                           (70,'Vans Old Skool 36 DX Anaheim Factory','1608625060270.jpg',60,'Vans old skool',265,4,'s30pc'),
                           (71,'\r\nVans Vault Authentic Chili Pepper - Ship US','1608774008706.jpg',65,'Vans authentic',343,1,'s20pc'),
                           (72,'Vans Era Patchwork Multi Color','1608774152453.jpg',78,'Vans authentic',547,1,'s10pc'),
                           (73,'Vans Authentic Caro Black White','1608774264295.jpg',60,'Vans authentic',743,1,'s20pc'),
                           (74,'Vans Vault OG Authentic LX Black White - Ship US','1608774321256.jpg',65,'Vans authentic',296,1,'default'),
                           (75,'Vans Vault Authentic Chili Pepper - Ship US','1608774363405.jpg',60,'Vans authentic',343,1,'s10pc'),
                           (77,'Vans Moma Classic Slip On','1609034545320.png',59,'Vans slip-on',834,6,'s10pc'),
                           (78,'Vans Slip-On Custom Culture Along with the Gods','1609034608943.png',56,'Vans slip-on',783,6,'s20pc'),
                           (79,'Vans Slip-On Alien Ghosts','1609034653526.png',45,'Vans slip-on',673,6,'default'),
                           (80,'Vans Slip-On Anaheim Factory Checkerboard','1609034696789.jpg',49,'Vans slip-on',348,6,'s20pc'),
                           (81,'Vans Slip-On Mix Checker Chili Pepper','1609034733814.jpg',78,'Vans slip-on',934,6,'s10pc'),
                           (82,'Vans Slip-On Stickers Mash Up','1609034816715.jpg',50,'Vans slip-on',582,6,'s10pc'),
                           (83,'Vans Slip-On Anaheim Factory Checkerboard','1609034871405.jpg',50,'Vans slip-on',563,6,'s20pc'),
                           (84,'Vans Slip-On Label Mix','1609034923703.jpg',55,'Vans slip-on',328,6,'s30pc'),
                           (85,'Vans Slip-On Mix Checker Chili Pepper','1609034970946.jpg',49,'Vans slip-on',921,6,'s10pc'),
                           (86,'Vans Slip-on Black Red F196 Checkerboard','1609035021120.jpg',59,'Vans slip-on',445,6,'s20pc'),
                           (87,'Vans Slip-On Mismatch','1609035081382.jpg',60,'Vans slip-on',562,6,'s20pc'),
                           (88,'Vans Era Forgotten Bones','1609035204723.jpg',78,'Vans ERA',765,3,'s10pc'),
                           (89,'Vans Era Lady Vans','1609035242491.jpg',60,'Vans ERA',452,3,'s20pc'),
                           (90,'Vans Era \"Get The Real\" Racing Red','1609035287852.jpg',45,'Vans ERA',293,3,'s20pc'),
                           (91,'Vans Era Deboss Checkerboard','1609035321871.jpg',50,'Vans ERA',546,3,'s30pc'),
                           (92,'Vans Era Comfycush Black','1609035380193.jpg',50,'Vans ERA',834,3,'s10pc'),
                           (93,'Vans Era Checkerboard Marshmallow','1609035436770.jpg',59,'Vans ERA',453,3,'s20pc'),
                           (94,'Checkerboard Classic Better Seller','1609036735269.png',60,'Vans classic',456,2,'s10pc'),
                           (95,'Checkerboard Classic Better Seller','1609036754525.png',55,'Vans classic',738,2,'s20pc'),
                           (96,'Checkerboard Classic Better Seller','1609036772150.png',49,'Vans classic',982,2,'s10pc'),
                           (97,'Checkerboard Classic Better Seller','1609036791385.png',59,'Vans classic',213,2,'s30pc'),
                           (98,'Checkerboard Classic Better Seller','1609036821201.png',78,'Vans classic',573,2,'s10pc'),
                           (99,'Checkerboard Classic Better Seller','1609036841790.jpg',59,'Vans classic',837,2,'s30pc'),
                           (100,'Checkerboard Classic Better Seller','1609036892365.jpg',60,'Vans classic',458,2,'s30pc'),
                           (101,'Checkerboard Classic Better Seller','1609036918234.png',49,'Vans classic',635,2,'s10pc'),
                           (107,'Checkerboard Classic Better Seller','1612540248195.jpg',64,'Vans classic',120,2,'s30pc');

DROP TABLE IF EXISTS role;

CREATE TABLE role(
    role_id number(50) NOT NULL AUTO_INCREMENT,
    role_name varchar(255) NOT NULL,
    primary key(role_id)
)

INSERT INTO role VALUES (1,'ROLE_ADMIN'),
                        (2,'ROLE_MEMBER'),
                        (3,'ROLE_USER');

DROP TABLE IF EXISTS sale;

CREATE TABLE sale(
    sale_id varchar(255) NOT NULL,
    sale_percent number(100) NOT NULL,
    primary key(sale_id)
)

INSERT INTO sale VALUES ('default',0),
                        ('s10pc',10),
                        ('s20pc',20),
                        ('s30pc',30),
                        ('s40pc',40),
                        ('s50',50);

DROP TABLE IF EXISTS user;

CREATE TABLE user (
    user_id number(50) NOT NULL AUTO_INCREMENT,
    address varchar(255),
    full_name varchar(255),
    gemder binary,
    image varchar(255),
    password varchar(255),
    phone varchar(255),
    role_id number(50),
    avatar varchar(255),
    email varchar(200),
    verify binary,
    primary key (user_id),
    foreign key role_id references role(role_id)
)

INSERT INTO user VALUES (31,'China - Tokyo - Mexico - Horykito','Mr. Admin',1,NULL,'$2a$10$KUTALTEnUXSZheyYXKyZw.mPIFgnwHxhyieKIrwRbrskBKPV.8u/u','0999999999',1,'1608484153089.png','admin@gmail.com',1),
                        (32,'Thai Binh - Gia Loc','Ha Pham Cuong',1,NULL,'$2a$10$sKYN4nrCNEm/LmYGZGSx3..XduqrTuqmasmooyxstfkygmh79c6Re','0362748475',2,'1608524412799.png','luxyre@gmail.com',0),
                        (33,'Koriert ytierhirer - Marikoriefrree - Mexico','Mrs. Acles Kuroier',0,NULL,'$2a$10$HMb8nsjSnhSr5FzD/vb3I.rG.f.hLP5qIb6oyJ7njWEVNBsSNHax.','0453726353',3,'1608540696277.png','sunfuric@gmail.com',0),
                        (34,'Colobola - Lumburamiter - Austraylisaanber','Mrs. Maria Mores',0,NULL,'$2a$10$RXZ7UGUWyfOjKwFRfoq8ceCZEXDE4/9ItsBzCRtEVIehPg9xXSXki','0462537263',2,'1608536572729.png','herfiter@gmail.com',0),
                        (35,'Mexico - Muroitoraka - Kukakiroierplo - Culalumbura','Mr. Profiler Lusises',1,NULL,'$2a$10$QEHUv/yAgL3mOvWYZdZwaeL7.cC82VYcxA.5kq0X/yyRBGnlsaicK','0874637464',3,'1612351921316.png','lokozama@gmail.com',1),
                        (36,'Phu Tho - Cao Bang','Mr. Long Body',1,NULL,'$2a$10$Cd5zbZU3NR4jCbGZB8v19elYJ24JFGJNQSXpVIN2Fkvm7JeykywsW','0973645345',2,'1608542398338.png','atermar@gmail.com',0),
                        (37,'Nam Dinh - Gia Loc - Hai Duong','Mr. Aloha',1,NULL,'$2a$10$ak76PjGuiUpR2Ln4Y.8Wke//JnWnad6bv8u7IydLMH0JufQ3Y9tha','0349347234',1,'1612540130590.png','aloha@gmail.com',0),
                        (40,'FPT University - Thach That - Ha Noi','Ha Van Quang',0,NULL,'$2a$10$d5NAjxsbcz7G251w2undHejUBfdZTvZxu8nrK8rgymoJiIVyUOoBq','0968904962',3,'1609219156198.png','coosi29@gmail.com',1),
                        (70,'Mexico - America - Suner','Mr.Alibaba',0,NULL,'$2a$10$UoDaMVKoHQUSQPgCh9z4b.z/InnYcI/8fvuTbNzVxn3Qq93SdVYFO','012-736-9403',3,'1608541027514.png','alibaba@gmail.com',1),
                        (73,NULL,NULL,0,NULL,'$2a$10$wQTtiuhxr7sMkeGE0GvA3OYsHCbR/47IzUlBryGdKd5l684StR/Re',NULL,3,'1609219156198.png','mist@gmail.com',1),
                        (74,NULL,NULL,0,NULL,'$2a$10$6NjaSR.IDkLhL.7wRUbbYO.FSWAxI1lmCdSVN.jTSwGtAWXbr340W',NULL,3,'1608484153089.png','tutitu@gmail.com',1),
                        (75,NULL,NULL,0,NULL,'$2a$10$IqI3L0h0u.PbJJ9ZBH7Kg.1FRl2uUv6RzkhCCxKTSVrMsrE7xcaLu',NULL,3,'1608484153089.png','vituver@gmail.com',1),
                        (76,'Southern California - America','Mr. Memory',0,NULL,'$2a$10$OEFg0tRbevdcwAp2d8gXW.xNSft55kBsHP6FDc.wOD1pe5/Nn.Dzy','043-045-3562',3,'1608484153089.png','memory@gmail.com',1),
                        (78,'Lai Cach - Xua Giang - Soc Son - Ha Noi','Mr. Quang',0,NULL,'$2a$10$BCAIKnWcbxUmj.81TxirUOtwlzDL1b2/FflK3xQ/G/NseBL/XdbMC','0946283645',3,'1608484153089.png','quanghvhe140233@fpt.edu.vn',1),
                        (79,'Futura - Tokyo - Japan','Mr. Discount',0,NULL,'$2a$10$LM7P5.BShyKGQnoXP6CACuEI7pOEQZJ.TA0e6oTdxr6KDXHLTKxPK','023-423-3826',3,'1608484153089.png','discount@gmail.com',1),
                        (80,NULL,NULL,0,NULL,'$2a$10$cKB21MKCWx8YkqLxqYjKHOT20uOKW.fgc3zjBdtf6fMxqdhw/6jBW',NULL,3,'1608484153089.png','nobita@gmail.com',1),
                        (81,'Atarakasta - Loberbitoi - Mohades','Mr. Suzuki',0,NULL,'$2a$10$6qXsuicu7WZmG6np7jQeaOU6zo/w5vDthejVUTVZjRR9wWEKv0DwC','0526357363',3,'1608484153089.png','suziki@gmail.com',1),
                        (82,NULL,NULL,0,NULL,'$2a$10$knB5HeS54LHcvvxS/XlNGOkqJF/OJqEiEQhAaxG2fl2uHWQlKPd6.',NULL,3,'1608484153089.png','vinhxiem2004@gmail.com',1),
                        (84,'132 Chu Van An - Cau Giay - Ha Noi','Mr. Create',1,NULL,'$2a$10$k5tDvXLhGg2a.w2qVi3P2egWtmLQCtGk1xmnjey7Z9UcnlyLOrmoy','0937352647',1,'1612349833775.png','create@gmail.com',0),
                        (88,NULL,NULL,0,NULL,'$2a$10$Sq1gFN4bhRTmC1WT8c6PJuBs9s0qtIA6Y.aongdJD3pt50c1YFB4K',NULL,3,'1608484153089.png','nguyendinhkhue14102000@gmail.com',1),
                        (89,NULL,NULL,0,NULL,'$2a$10$dgttFgJkC.8ogpd7hjWHyuTSw/loUnu8yKQlthgL678GGH0QP8wH6',NULL,3,'1608484153089.png','sads@gmail.com',0),
                        (90,NULL,NULL,0,NULL,'$2a$10$2pJAHf1spIjeDhm40B.fWuxNi5UxPn6ztXU9wKLH1VL4YdZ4VWdp.',NULL,3,'1608484153089.png','QDSF@gmail.com',0),
                        (91,NULL,NULL,0,NULL,'$2a$10$5.pCxBFFYzMq7056r6TNNODzOfC/FyFuakI8EeywoQmVHUjU61oxa',NULL,3,'1608484153089.png','ass1d@gmail.com',0),
                        (92,NULL,NULL,0,NULL,'$2a$10$A8r57H93QmIikC4gBQwuL.M1L7AnGRXWzByJvzwnQP/PfZhBUxopW',NULL,3,'1608484153089.png','lucurata@gmail.com',1),
                        (93,NULL,NULL,0,NULL,'$2a$10$idBT4HXAlkdaBws8PJBryueDlj1sOApoOb2HEEITPATB1Wkyjlqca',NULL,3,'1608484153089.png','locota@gmail.com',0),
                        (94,NULL,NULL,0,NULL,'$2a$10$CEwil.cgd.N9LyPIx3mIrO5qGCdAiaZlVoNaKVIefsA5vwVh7oQn2',NULL,3,'1608484153089.png','lukuta@gmail.com',1),
                        (95,'FPT University - Hoa Lac - Thach That - Ha Noi','Mr. Mo Mo',0,NULL,'$2a$10$vfbGaiCUzXnAJL5gX5EmmeoKgDfFmuTVzoCUR6QXc.gwDpKj4zuyC','0973645345',3,'1608484153089.png','amomo@gmail.com',1);