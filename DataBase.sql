drop database if exists onlinePos;
create database if not exists onlinePos;
use onolinePos;

create table customer (
    id VARCHAR(30),
    name VARCHAR(250),
    address VARCHAR(250),
    salary  int
)

insert into customer (id,name,address,salary) values (?,?,?,?)