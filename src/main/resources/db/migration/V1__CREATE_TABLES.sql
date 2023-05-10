create table hibernate_sequence(
    next_val bigint
)engine=MyISAM;

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

CREATE TABLE order_drink (
                             drink_id BIGINT NOT NULL,
                             order_id BIGINT NOT NULL,
                             PRIMARY KEY (drink_id, order_id),
                             FOREIGN KEY (drink_id) REFERENCES drinks (id),
                             FOREIGN KEY (order_id) REFERENCES orders (id)
) engine=MyISAM;


CREATE TABLE drinks
(
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL,
    description    VARCHAR(255) NOT NULL,
    section VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    PRIMARY KEY (id)
) engine=MyISAM;

CREATE TABLE users(
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      password VARCHAR(255) NOT NULL,
                      role VARCHAR(255) NOT NULL,
                      username VARCHAR(255) NOT NULL,
                      PRIMARY KEY (id)
) engine=MyISAM;

CREATE TABLE orders
(
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) engine=MyISAM;




