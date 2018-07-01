insert into user(account,password,realname,email,phone) values ('u1','$2a$10$69uF8J5HJ2AYSIRVaALXM.162zjD7DvzGMd4QLCtY/Lstk9/vJh5O','u1','u1','u1');
insert into user(account,password,realname,email,phone) values ('u2','$2a$10$LhlZmgMyDj04g52ICzVS2eQ7oH3xuzS5Po8rh.p31q4Z0Ibwmy3ay','u2','u2','u2');
insert into user(account,password,realname,email,phone) values ('u3','$2a$10$t6QxdDqYLD4ftYFmuABxqenegx7e7qTj4gefM0Ul/qxD1uv3XwS7.','u3','u3','u3');

insert into goods_category (name) values ('c1');

insert into goods(category_id,name,price,stock,introduce) values (1,'g1','1',100,'g1');
insert into goods(category_id,name,price,stock,introduce) values (1,'g2','2',100,'g2');
insert into goods(category_id,name,price,stock,introduce) values (1,'g3','3',100,'g3');

insert into role(name) values ('ROLE_USER');
insert into role(name) values ('ROLE_ADMIN');

insert into user_in_role (uid, rid) values (1,1);
insert into user_in_role (uid, rid) values (2,2);

insert into orders (uid, payment, post_fee, address, status) values ('1',234,20,'address1',0);
insert into orders (uid, payment, post_fee, address, status) values ('1',4000,0,'address1',0);

insert into order_item(oid,gid,num,price) values (1,1,10,10);
insert into order_item(oid,gid,num,price) values (1,2,15,100);