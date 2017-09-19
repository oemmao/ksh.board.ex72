CREATE TABLE tb_calData (
   id  int NOT NULL auto_increment,
   op1  int NOT NULL,
   op  varchar(5) NOT NULL,
   op2  int NOT NULL,
   result  int NOT NULL,
   primary key (id)
);

select * from tb_calData;

truncate table tb_calData;

delete from tb_calData;

drop table tb_calData;

drop Sequence seq_calData;



/*
ALTER TABLE tb_calData ADD PRIMARY KEY (id);

Create Sequence seq_calData
start with 1
increment by 1
maxvalue 10000000;

drop Sequence seq_calData;
*/