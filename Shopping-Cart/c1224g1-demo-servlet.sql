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