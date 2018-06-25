drop table if exists userinrole;
drop table if exists orders;
drop table if exists goods;
drop table if exists user;
drop table if exists producer;
drop table if exists role;
create table user(
    id bigint primary key auto_increment,
    account varchar(20) not null,
    password varchar(20) not null,
    realname varchar(20) not null,
    email varchar(50) not null,
    phone varchar(11) not null,
    registertime date not null,
    online enum('y','n') default 'n' not null
);
create table producer(
    id bigint primary key auto_increment,
    name varchar(30) not null,
    phone varchar(11) not null,
    address varchar(30) not null
);

create table goods(
    id bigint primary key auto_increment,
    name varchar(30) not null,
    price int not null,
    introduce varchar(100) not null,
    pid bigint,
    foreign key(pid) references producer(id)
);

create table orders(
    id bigint primary key auto_increment,
    uid bigint,
    gid bigint,
    number int not null,
    dealdate date not null,
    foreign key(uid) references user(id),
    foreign key(gid) references goods(id)
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



insert into user(account,password,realname,email,phone,registertime) values ('u1','u1','u1','u1','u1','2018-01-01');
insert into user(account,password,realname,email,phone,registertime) values ('u2','u2','u2','u2','u2','2018-01-01');
insert into user(account,password,realname,email,phone,registertime) values ('u3','u3','u3','u3','u3','2018-01-01');

insert into producer(name,phone,address) values ('p1','123','123');

insert into goods(name,price,introduce,pid) values ('g1','1','g1','1');
insert into goods(name,price,introduce,pid) values ('g2','2','g2','1');
insert into goods(name,price,introduce,pid) values ('g3','3','g3','1');

insert into role(name) values ('user');
insert into role(name) values ('admin');

insert into userinrole (uid, rid) values (1,1);
insert into userinrole (uid, rid) values (2,2);