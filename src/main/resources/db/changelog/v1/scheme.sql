DROP TABLE IF EXISTS product, role, users;

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