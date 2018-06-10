create table if not exists user(
    id int primary key auto_increment,
    account varchar(255) not null,
    password varchar(255) not null,
    name varchar(255) not null,
    age int not null
);