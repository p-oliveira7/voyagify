create table users(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    country varchar(255),
    user_type varchar(50) not null,
    password varchar(255) not null,

     primary key(id)
);