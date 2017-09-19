CREATE TABLE tb_excMsg (
   id  int NOT NULL auto_increment,
   code  int NOT NULL,
   message  varchar(20) NOT NULL,
   primary key (id)
);

select * from tb_excMsg;

insert into tb_excMsg(code, message) values(181, 'AddZeroException');
insert into tb_excMsg(code, message) values(182, 'SubZeroException');
insert into tb_excMsg(code, message) values(183, 'MulOneException');
insert into tb_excMsg(code, message) values(184, 'DivOneException');

drop table tb_excMsg;

/*
ALTER TABLE tb_excMsg ADD PRIMARY KEY (id);

Create Sequence seq_excMsg
start with 1
increment by 1
maxvalue 10000000;
*/