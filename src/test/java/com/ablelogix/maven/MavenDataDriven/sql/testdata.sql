drop table AbleLogix_LoginSuiteAdmin;
  create table AbleLogix_LoginSuiteAdmin
	(serialno number(10) primary key,
	username varchar(100),
	password varchar(100),
	expectedresu1t varchar(200),
	data2run varchar(10),
	pass_fail_skip varchar(100));
	
insert into AbleLogix_LoginSuiteAdmin(serialno,username,password,expectedresu1t)
values
(1,'admin','admin','Welcome Admin');

insert into AbleLogix_LoginSuiteAdmin(serialno,username,password, expectedresu1t)
values
(2,'rafic','shafi','Welcome Admin');


update AbleLogix_LoginSuiteAdmin
set data2run = 'Y'
where serialno = 1

update AbleLogix_LoginSuiteAdmin
set data2run = 'N'
where serialno = 2

select * from AbleLogix_LoginSuiteAdmin


-------------
drop table AbleLogix_LoginSuiteEssUser;
  create table AbleLogix_LoginSuiteEssUser
	(serialno number(10) primary key,
	username varchar(100),
	password varchar(100),
	expectedresu1t varchar(200),
	data2run varchar(10),
	pass_fail_skip varchar(100));
	
insert into AbleLogix_LoginSuiteEssUser(serialno,username,password,expectedresu1t)
values
(1,'linda.anderson','linda.anderson','Welcome Linda');

insert into AbleLogix_LoginSuiteEssUser(serialno,username,password, expectedresu1t)
values
(2,'rafic','shafi','Welcome Linda');


--------------
drop table AbleLogix_AddEmp;
  create table AbleLogix_AddEmp
	(serialno number(10) primary key,
	username varchar(100),
	password varchar(100),
	fname varchar(200),
	lname varchar(200),
	location varchar(200),
	data2run varchar(10),
	pass_fail_skip varchar(100));
	
insert into AbleLogix_AddEmp
(serialno,username,password,fname,lname,location)
values
(1,'admin','admin','','shaik','HQ - CA, USA');

insert into AbleLogix_AddEmp
(serialno,username,password,fname,lname,location)
values
(2,'admin','admin','rafi','','HQ - CA, USA');


insert into AbleLogix_AddEmp
(serialno,username,password,fname,lname,location)
values
(3,'admin','admin','rafi','shaik','-- Select --');


insert into AbleLogix_LoginSuiteEssUser(serialno,username,password, expectedresu1t)
values
(2,'rafic','shafi','Welcome Linda');


