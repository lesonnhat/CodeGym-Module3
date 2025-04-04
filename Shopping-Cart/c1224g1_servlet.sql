create database c1224g1_servlet;

use c1224g1_servlet;

create table Product (
id int not null primary key auto_increment,
name varchar(45) not null,
price decimal(10,2) not null
);

insert into Product
values (1, "Iphone", 2300),
(2, "Samsung", 2150),
(3, "Xiaomi", 1890),
(4, "Oppo", 1700),
(5, "Nokia", 1900);