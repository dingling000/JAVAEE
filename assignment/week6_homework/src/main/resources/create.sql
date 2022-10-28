create table product.product(
                                id bigint(20) primary key AUTO_INCREMENT,
                                name varchar(32) not null,
                                price float,
                                stock_quantity float

);


create table product.supplier(
                                 id bigint(20) primary key AUTO_INCREMENT,
                                 name varchar(32) not null,
                                 phone varchar(32) not null

);