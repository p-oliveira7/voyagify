create table reviews(

    id bigint not null auto_increment,
    user_id bigint not null,
    text varchar(2000) not null,

     primary key(id),

     constraint fk_reviews_user_id foreign key (user_id) references users(id)
);