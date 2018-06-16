drop table if exists user;
create table user(
    id bigint primary key auto_increment,
    account varchar(20) not null,
    password varchar(20) not null,
    realname varchar(20) not null,
    email varchar(50) not null,
    phone varchar(11) not null,
    registertime date not null
);
drop table if exists producer;
create table producer(
    id bigint primary key auto_increment,
    name varchar(30) not null,
    phone varchar(11) not null,
    address varchar(30) not null
);
drop table if exists goods;
create table goods(
    id bigint primary key auto_increment,
    name varchar(30) not null,
    price int not null,
    introduce varchar(100) not null,
    pid bigint,
    foreign key(pid) references producer(id)
);
drop table if exists orders;
create table orders(
    id bigint primary key auto_increment,
    uid bigint,
    gid bigint,
    number int not null,
    dealdate date not null,
    foreign key(uid) references user(id),
    foreign key(gid) references goods(id)
);