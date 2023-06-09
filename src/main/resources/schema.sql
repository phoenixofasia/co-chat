--DROP TABLE USER;
--DROP TABLE TRANSACTIONS;
--CREATE TABLE IF NOT EXISTS USER (ID INT PRIMARY KEY, USERNAME VARCHAR(30), EMAIL VARCHAR(30),PASSWORD VARCHAR(30) );
drop table IF EXISTS users;
drop table IF EXISTS authorities ;
create table users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(50) not null,
	email varchar(100) not null,
	enabled boolean not null
);

create table authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

create table persistent_logins (
    username varchar(64) primary key,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    constraint fk_persistent_logins_users foreign key(username) references users(username)
);