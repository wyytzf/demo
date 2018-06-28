drop table if exists userinrole;
drop table if exists order_item;
drop table if exists orders;
drop table if exists goods;
drop table if exists user;
drop table if exists role;
create table user(
    id bigint primary key auto_increment,
    account varchar(20) not null,
    password varchar(20) not null,
    realname varchar(20) not null,
    email varchar(50) not null,
    phone varchar(11) not null,
    registertime date not null
);

create table goods(
    id bigint primary key auto_increment,
    name varchar(30) not null,
    price double not null,
    introduce varchar(100) not null
);

create table role (
  id bigint primary key auto_increment,
  name varchar(20) not null
);

create table userinrole(
  id bigint primary key auto_increment,
  uid bigint,
  rid bigint,
  foreign key(uid) references user(id),
  foreign key(rid) references role(id)
);

create table orders(
    id bigint primary key auto_increment,
    uid bigint not null,
    payment varchar(20),
    post_fee varchar(20),
    address varchar(100),
    create_time datetime,
    payment_time datetime,
    foreign key(uid) references user(id)
);

create table order_item(
    id bigint primary key auto_increment,
    oid bigint not null,
    gid bigint not null,
    num int not null,
    price double not null,
    foreign key(oid) references orders(id),
    foreign key(gid) references goods(id)
);


insert into user(account,password,realname,email,phone,registertime) values ('u1','u1','u1','u1','u1','2018-01-01');
insert into user(account,password,realname,email,phone,registertime) values ('u2','u2','u2','u2','u2','2018-01-01');
insert into user(account,password,realname,email,phone,registertime) values ('u3','u3','u3','u3','u3','2018-01-01');

insert into goods(name,price,introduce) values ('g1','1','g1');
insert into goods(name,price,introduce) values ('g2','2','g2');
insert into goods(name,price,introduce) values ('g3','3','g3');

insert into role(name) values ('ROLE_USER');
insert into role(name) values ('ROLE_ADMIN');

insert into userinrole (uid, rid) values (1,1);
insert into userinrole (uid, rid) values (2,2);

insert into orders (uid, payment, post_fee, address, create_time, payment_time) values ('1',234,20,'address1','2018-01-01 12:00:00','2018-01-01 12:00:00');
insert into orders (uid, payment, post_fee, address, create_time, payment_time) values ('1',4000,0,'address1','2018-01-02 13:00:00','2018-01-03 12:00:00');

insert into order_item(oid,gid,num,price) values (1,1,10,10);
insert into order_item(oid,gid,num,price) values (1,2,15,100);