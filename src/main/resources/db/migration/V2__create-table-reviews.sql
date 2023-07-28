create table reviews(

    id bigint not null auto_increment,
    user_id varchar(100) not null,
    text varchar(2000) not null,

     primary key(id)
);