DROP TABLE if exists product,
    role,
    users,
    client,
    client_bonus_card cascade;

CREATE TABLE IF NOT EXISTS product (
     id SERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     description TEXT
);

CREATE TABLE if not exists role
(
    id   BIGSERIAL    NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE if not exists users
(
    id           BIGSERIAL    NOT NULL PRIMARY KEY,
    role_id      bigint REFERENCES role (id) ON DELETE CASCADE,
    username     varchar(255) NOT NULL,
    password     varchar(255) NOT NULL,
    first_name   varchar(255) NOT NULL,
    last_name    varchar(255) NOT NULL,
    email        varchar(255) NOT NULL,
    phone_number varchar(255) not null,
    UNIQUE (username)
);

create table if not exists client
(
    id                 bigserial not null primary key,
    chat_id            bigint not null unique,
    name               varchar(255),
    birthday           date,
    email              varchar(255),
    registration_state varchar(255)
);

create table if not exists client_bonus_card (
     id                 bigserial not null primary key,
     client_id          bigint unique references client(id) on delete cascade,
     amount             int not null default 0,
     discount_percent   int not null default 5
);