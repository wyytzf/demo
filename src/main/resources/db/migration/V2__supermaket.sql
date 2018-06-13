drop table user
create table user{
    id bigint  primary key auto_increment,
    account varchar(20) not null,
    password varchar(20) not null,
    realname varchar(20) not null,
    eMail varchar(50) not null,
    phone varchar(11) not null.
    register_time date not null
};
create table goods{
    id bigint primary key auto_increment,
    name varchar(30) not null,
    price int not null,
    introduce varchar(100) not null,
    pid bigint foreign key references producer(id)
};
create table producer{
    id bigint primary key auto_increment,
    name varchar(30) not null,
    phone varchar(11) not null,
    address varchar(30) not null
};
create table order{
    id bigint primary key auto_increment,
    uid bigint foreign key references user(id),
    gid bigint foreign key references goods(id),
    number int not null,
    deal_date date not null
};