create database inventory;
use inventory;

create table task(
id integer auto_increment primary key,
name varchar(25) not null,
description varchar(100),
status varchar(10) not null);

insert into task values (1,'abc','description','PENDING');

select * from task;
