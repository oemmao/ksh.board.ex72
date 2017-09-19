CREATE TABLE tb_rankData (
   id  int NOT NULL auto_increment,
   sno varchar(20) NOT NULL,
   name varchar(20) NOT NULL,
   sub1 varchar(20) NOT NULL,
   sub2 varchar(20) NOT NULL,
   sub3 varchar(20) NOT NULL,
   sum varchar(20) NOT NULL,
   avg decimal(5,2) NOT NULL,
   rank varchar(20) NOT NULL,
   bigo varchar(20) NOT NULL,
   primary key (id)
);

select * from tb_rankData;

truncate table tb_rankData;

delete from tb_rankData;
drop table tb_rankData;
drop Sequence seq_rankData;

/*
ALTER TABLE tb_rankData ADD PRIMARY KEY (id);

Create Sequence seq_rankData
start with 1
increment by 1
maxvalue 10000000;
*/