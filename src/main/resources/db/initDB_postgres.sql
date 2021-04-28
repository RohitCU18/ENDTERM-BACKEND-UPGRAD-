DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS user_records;
DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR NOT NULL,
  surName     VARCHAR NOT NULL,
  phonenumber VARCHAR NOT NULL,
  homeNumber  VARCHAR,
  address     VARCHAR,
  email       VARCHAR,
  password    VARCHAR NOT NULL
);
CREATE UNIQUE INDEX users_unique_phonenumber_idx
  ON users (phonenumber);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE persistent_logins (
  username  VARCHAR(64) NOT NULL,
  series    VARCHAR(64) PRIMARY KEY,
  token     VARCHAR(64) NOT NULL,
  last_used TIMESTAMP   NOT NULL
);

CREATE TABLE user_records
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR NOT NULL,
  surName     VARCHAR NOT NULL,
  phonenumber VARCHAR NOT NULL,
  email       VARCHAR,
  user_id     INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE INDEX user_records_user_idx
  ON user_records (user_id);
