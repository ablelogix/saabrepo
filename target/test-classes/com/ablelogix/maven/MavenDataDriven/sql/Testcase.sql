drop table ablelogix_TestcasesList
create table ablelogix_TestcasesList
(TestCaseName varchar(50),
case2run varchar(10),
pass_fail_skip varchar(100))


insert into ablelogix_TestcasesList (TestCaseName) values ('LoginSuiteAdmin');

insert into ablelogix_TestcasesList (TestCaseName) values ('LoginSuiteESSUser');

insert into ablelogix_TestcasesList (TestCaseName) values ('LoginSuiteFirstLevelSupervisor');

insert into ablelogix_TestcasesList (TestCaseName) values ('LoginSuiteSecondLevelSupervisor');
