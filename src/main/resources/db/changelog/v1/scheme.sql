DROP TABLE if exists unit,
    menu_item,
    category,
    product,
    role,
    users,
    client,
    client_bonus_card cascade;

CREATE TABLE if not exists category
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE if not exists unit
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE if not exists product
(
    id          BIGSERIAL PRIMARY KEY,
    category_id bigint references category (id) on delete cascade,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE menu_item
(
    id         BIGSERIAL PRIMARY KEY,
    product_id BIGINT         NOT NULL REFERENCES product (id),
    unit_id    BIGINT         NOT NULL REFERENCES unit (id),
    quantity   DECIMAL(5, 2)  NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (unit_id) REFERENCES unit (id)
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
    phone              varchar(50) unique,
    registration_state varchar(255) CHECK (registration_state IN ('INIT', 'ASK_NAME', 'ASK_EMAIL', 'ASK_PHONE', 'ASK_BIRTHDATE', 'REGISTERED'))
);

create table if not exists client_bonus_card (
     id                 bigserial not null primary key,
     client_id          bigint unique references client(id) on delete cascade,
     amount             int not null default 0,
     discount_percent   int not null default 5
);

CREATE TABLE IF NOT EXISTS orders
(
    id                      BIGSERIAL PRIMARY KEY,
    client_id               BIGINT REFERENCES client(id),
    bonus_points_action     VARCHAR(5) CHECK (bonus_points_action IN ('USE', 'EARN')),
    user_id                 BIGINT NOT NULL REFERENCES users(id),
    total_amount            DECIMAL(10, 2) NOT NULL,
    discount_amount         DECIMAL(10, 2) NOT NULL,
    final_amount            DECIMAL(10, 2) NOT NULL,
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_item
(
    id            BIGSERIAL PRIMARY KEY,
    order_id      BIGINT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    menu_item_id  BIGINT NOT NULL REFERENCES menu_item(id),
    quantity      DECIMAL(5, 2) NOT NULL,
    price         DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS client_bonus_card_transaction
(
    id                BIGSERIAL PRIMARY KEY,
    order_id          BIGINT REFERENCES orders(id) ON DELETE CASCADE,
    transaction_type  VARCHAR(50) NOT NULL CHECK (transaction_type IN ('CREDIT', 'DEBIT')),
    points            INT NOT NULL,
    transaction_date  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);