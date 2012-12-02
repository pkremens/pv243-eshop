-- You can use this file to load seed data into the database using SQL statements
insert into Customer (email, name, password, role) values ('abc@abc.cz', 'abc', '5823106a$b8deb7dc1edfe6fdede502c017efbac82946351de72031cbe0c2dc9a798eccf13c16c0ef556406a2def25c845c5982dc118740a812a630c03ffb31bcb4de33df', 'user');
insert into Customer (email, name, password, role) values ('seller@abc.cz', 'abc', '5823106a$b8deb7dc1edfe6fdede502c017efbac82946351de72031cbe0c2dc9a798eccf13c16c0ef556406a2def25c845c5982dc118740a812a630c03ffb31bcb4de33df', 'seller');
insert into Customer (email, name, password, role) values ('admin@abc.cz', 'abc', '5823106a$b8deb7dc1edfe6fdede502c017efbac82946351de72031cbe0c2dc9a798eccf13c16c0ef556406a2def25c845c5982dc118740a812a630c03ffb31bcb4de33df', 'admin');
insert into Product (id, name, price, visible) values (10000, 'stuff', 123, true);
insert into Product (id, name, price, visible) values (10001, 'broken pipe', 1234, true);
insert into Product (id, name, price, visible) values (10002, 'pocket knife', 234, false);
insert into Product (id, name, price, visible) values (10003, 'duck tape', 34, true);
insert into Product (id, name, price, visible) values (10004, 'BFG 9000', 11111134, true);
insert into Book (description, illustrations, isbn, nbOfPage, title, id) values ('description', true, 1232131, 23, 'title', 1000);
insert into Book (description, illustrations, isbn, nbOfPage, title, id) values ('description2', true, 14232131, 23, 'title2', 1001);
insert into Book (description, illustrations, isbn, nbOfPage, title, id) values ('description3', true, 124232131, 23, 'title3', 1002);
insert into Book (description, illustrations, isbn, nbOfPage, title, id) values ('description4', true, 1232342131, 23, 'title4', 1003);
insert into Book (description, illustrations, isbn, nbOfPage, title, id) values ('description5', true, 12322134131, 23, 'title5', 1004);