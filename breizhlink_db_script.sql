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

INSERT INTO `user` (`id`, `id_status`, `username`, `password`, `mail`, `account_activated`) VALUES
(1, 1, 'user', '$2a$10$DhFghP9eBxasGPMYb7o3FeGVPiT6X6QpqlXCF9ydG82iFxHS/tUKy', 'user@user.com', 1);

INSERT INTO `url` (`id`, `id_user`, `source_url`, `short_url`, `mail`, `creation_date`, `start_date`, `end_date`, `captcha`, `url_type`) VALUES
(1, 1, 'https://www.reddit.com/r/funny/', '2bTW1', '', '2018-07-28', NULL, NULL, 0, 'AuthenticatedUrl');

INSERT INTO `stats` (`id`, `id_url`, `nb_click`, `date`, `ip_address`) VALUES
(1, 1, 7, '2018-07-28', '0.0.0.0'),
(2, 1, 5, '2018-07-28', '1.1.1.1'),
(3, 1, 19, '2018-07-30', '0.0.0.0'),
(4, 1, 6, '2018-08-03', '0.0.0.0'),
(5, 1, 23, '2018-08-15', '1.1.1.1');

