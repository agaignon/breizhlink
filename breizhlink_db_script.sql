CREATE DATABASE breizhlink;

create table status (
id serial,
name text,
primary key (id)
);

create table user (
id serial,
id_status bigint unsigned,
username varchar(255) unique,
password text,
mail text,
account_activated boolean,
primary key (id),
foreign key (id_status) references status(id)
);

create table url (
id serial,
id_user bigint unsigned,
source_url text,
short_url varchar(5) unique,
mail text,
creation_date date,
start_date date,
end_date date,
captcha boolean,
url_type text,
primary key (id),
foreign key (id_user) references user(id)
);

create table stats (
id serial,
id_url bigint unsigned,
nb_click int,
date date,
ip_address text,
primary key (id),
foreign key (id_url) references url(id)
);

create table url_passwords (
id serial,
id_url bigint unsigned,
password text,
primary key (id),
foreign key (id_url) references url(id)
);

insert into status
values (1, "INDIVIDUAL"),
(2, "COMPANY"),
(3, "ASSOCIATION");





