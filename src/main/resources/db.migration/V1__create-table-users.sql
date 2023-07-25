create table users(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    country VARCHAR(255),
    user_type VARCHAR(50), not null
    password VARCHAR(255) NOT NULL
);