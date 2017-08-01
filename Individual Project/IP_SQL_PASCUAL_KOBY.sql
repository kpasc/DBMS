drop table transaction;
drop table assemblyaccount;
drop table fitprocess;
drop table paintprocess;
drop table cutprocess;
drop table processaccount;
drop table cutjob;
drop table paintjob;
drop table fitjob;
drop table job;
drop table process;
drop table departmentaccount;
drop table department;
drop table account;
drop table assembly;
drop table customer;


create table customer
(
  name varchar(30) not null,
  address varchar(30),
  primary key(name)
);

create table assembly
(
  ID numeric(5,0) not null,
  date_ordered date,
  details varchar(100),
  cust varchar(30) not null,
  primary key(ID),
  foreign key(cust) references customer(name)
);

create table department
(
  no numeric(5,0) not null,
  data varchar(100),
  primary key(no)
);

create table process
(
  ID numeric(5,0) not null,
  data varchar(100),
  ass_id numeric(5,0) not null,
  dep_no numeric(5,0) not null,
  type varchar(10),
  primary key(ID),
  foreign key(ass_id) references assembly(ID),
  foreign key(dep_no) references department(no)
);

create table fitprocess
(
  ID numeric(5,0) not null references process(ID),
  fit_type varchar(50),
  primary key(ID)
);

create table paintprocess
(
  ID numeric(5,0) not null references process(ID),
  paint_type varchar(50),
  paint_method varchar(50),
  primary key(ID)
);

create table cutprocess
(
  ID numeric(5,0) not null references process(ID),
  cut_type varchar(50),
  machine_type varchar(50),
  primary key(ID)
);

create table job
(
  no numeric(5,0) not null,
  date_commenced date,
  date_completed date,
  information varchar(100),
  pro_no numeric(5,0) not null,
  ass_id numeric(5,0) not null,
  primary key(no),
  foreign key(pro_no) references process(ID),
  foreign key(ass_id) references assembly(ID)
);

create table cutjob
(
  no numeric(5,0) not null references job(no),
  machine_used varchar(50),
  time_used numeric(6,2),
  material_used varchar(20),
  labor_time numeric(6,2),
  primary key(no)
);

create table paintjob
(
  no numeric(5,0) not null references job(no),
  color varchar(10),
  volume varchar(10),
  labor_time numeric(6,2),
  primary key(no)
);

create table fitjob
(
  no numeric(5,0) not null references job(no),
  labor_time numeric(6,2),
  primary key(no)
);

create table account
(
  no numeric(5,0) not null,
  date_established date,
  primary key(no)
);

create table assemblyaccount
(
  no numeric(5,0) not null references account(no),
  record_costs numeric(8,2),
  ass_id numeric(5,0) not null,
  primary key(no),
  foreign key(ass_id) references assembly(ID)
);

create table departmentaccount
(
  no numeric(5,0) not null references account(no),
  record_costs numeric(8,2),
  dept_id numeric(5,0) not null,
  primary key(no),
  foreign key(dept_id) references department(no)
);

create table processaccount
(
  no numeric(5,0) not null references account(no),
  record_costs numeric(8,2),
  pro_id numeric(5,0) not null,
  primary key(no),
  foreign key(pro_id) references process(ID)
);

create table transaction
(
  no numeric(5,0) not null,
  sup_cost numeric(8,2),
  job_no numeric(5,0) not null,
  primary key(no),
  foreign key(job_no) references job(no)
);
