INSERT INTO role(name) VALUES ('ROLE_USER');
INSERT INTO role(name) VALUES ('ROLE_ADMIN');

INSERT INTO users(role_id, username, password, first_name, last_name, email, phone_number)
VALUES ((select id from role where name = 'ROLE_ADMIN'), 'admin', '$2a$10$.qxEMnRmewbe0jB/XpBbPefHgX5orMeFum7760wBEf9Ony0DPMFii', 'админ', 'админ', 'admin@mail.ru', '123123');

INSERT INTO users(role_id, username, password, first_name, last_name, email, phone_number)
VALUES ((select id from role where name = 'ROLE_USER'), 'demo', '$2a$10$vYLfdQHK9VYcPXLpmFjU5eHwbIK7BAfavjjsN4ZSFJAakHeDtBzxG', 'demo', 'demo', 'demo@mail.ru', '123123');