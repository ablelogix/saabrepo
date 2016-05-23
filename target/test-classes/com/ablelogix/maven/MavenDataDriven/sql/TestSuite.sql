drop table ablelogix_SuitesList
create table ablelogix_SuitesList
(suiteName varchar(100) primary key,
suitetorun varchar(10),
skipped_executed varchar(100))

insert into ablelogix_SuitesList (suiteName) values ('LoginSuite')

insert into ablelogix_SuitesList (suiteName) values ('AddEmployee')

select * from ablelogix_SuitesList


update orangehrm_SuitesList set suitetorun='Y' where suiteName='LoginSuite'

update orangehrm_SuitesList set suitetorun='N' where suiteName='addemployee'

select * from orangehrm_SuitesList
where suiteName='LoginSuite'

select * from orangehrm_SuitesList
where suiteName='AddEmployee'
