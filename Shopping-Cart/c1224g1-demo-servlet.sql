delimiter //
create procedure sp_get_all_products()
begin
select * from product;
end //
delimiter ;

call sp_get_all_products();

delimiter //
create procedure sp_insert_product(
in _name varchar(255),
in _price decimal(10,2)
)
begin
insert into product(name, price) values(_name, _price);
end //
delimiter ;

delimiter //
create procedure sp_find_product_by_id(
in _id int
)
begin
select * from product 
where id = _id;
end //
delimiter ;

delimiter //
create procedure sp_update_product(
in _id int,
in _name varchar(255),
in _price decimal(10,2)
)
begin
update product
set name = _name, price = _price 
where id = _id;
end //
delimiter ;

delimiter //
create procedure sp_insert_order(
in _order_date datetime,
in _total_price decimal(10,2),
out _order_id int
)
begin
insert into orders(order_date, total_price) values(_order_date, _total_price);
set _order_id = (select id from orders order by id desc limit 1);
end //
delimiter ;

set @id = -1;
call sp_insert_order('2022-3-3', 29, @id);
select @id;

delimiter //
create procedure sp_insert_order_detail(
in _order_id int,
in _product_id int,
in _quantity int
)
begin
insert into orderdetail(order_id, product_id, quantity) values(_order_id, _product_id, _quantity);
end //
delimiter ;




