CREATE TABLE tb_userdata (
   id int not null auto_increment,
   userId varchar(20) not null,
   pwd varchar(20) not null,
   name varchar(20) not null,
   phone varchar(20) not null,
   address varchar(100) not null,
   primary key (id)
);

select * from tb_userdata;

truncate table tb_userdata;

delete from tb_userdata;

drop Sequence seq_userdata;

drop table tb_userdata;



/*
ALTER TABLE tb_userdata ADD PRIMARY KEY (id);

Create Sequence seq_userdata
start with 1
increment by 1
maxvalue 10000000;
*/