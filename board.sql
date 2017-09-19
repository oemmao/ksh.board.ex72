drop table board;
create table board (
	num  int  not  null  auto_increment,
	writer   varchar(20),
	email   varchar(100),	
	subject   varchar(100),	
	passwd   varchar(10),
	reg_date   datetime,
	readcount  int,
	ref   int,
	re_step   int,
    re_level  int,
	content    text,
    ip   varchar(20),
	primary key (num)
);


select subject, ref, re_step, re_level from board;

select max(num) from board;

select * from board;

delete from board;