DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS product (
     id SERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     description TEXT
);