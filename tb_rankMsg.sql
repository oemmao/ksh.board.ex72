create table tb_rankMsg (
	id int not null auto_increment,
	code int not null,
	msg varchar(20) not null,
	primary key (id)
);

select * from tb_rankMsg;

insert into tb_rankMsg(code, msg) values(181, '°ú¶ô');
insert into tb_rankMsg(code, msg) values(182, 'Å»¶ô');

drop table tb_rankMsg;
drop table seq_rankMsg;


/*
alter table tb_rankMsg add primary key (id);

Create Sequence seq_rankMsg
start with 1
increment by 1
maxvalue 10000000;
*/