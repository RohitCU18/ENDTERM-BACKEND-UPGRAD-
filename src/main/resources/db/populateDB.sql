DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM user_records;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, surname, phonenumber, password)
VALUES ('Sergiy', 'Dyrda', '+38(068)044-12-28', '$2a$10$JpweqaQFuIQXbtjtYi/7D.abqlboIi11UwRKewOGg/vCuSho9am1.'), --password user_sergiy
  ('Anastasia', 'Kropyva', '+38(097)287-65-36', '$2a$10$FzlzXwKeJmYSBPlADh.x3uTnGv0WAqOVCkAQkmIq7pcRyvBa.wgau'); --password anasteisha


INSERT INTO user_roles (role, user_id) VALUES
  ('USER', 100000),
  ('USER', 100001);

INSERT INTO user_records (name, surname, phonenumber, email, user_id)
VALUES ('Name11', 'Surname11', '+38(095)123-45-67', 'name11@mail.com', 100000),
  ('Name12', 'Surname12', '+38(095)987-65-43', 'name12@mail.com', 100000),
  ('Name21', 'Surname21', '+38(095)645-49-71', 'name21@mail.com', 100001),
  ('Name22', 'Surname22', '+38(095)432-21-46', 'name22@mail.com', 100001);
