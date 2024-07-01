USE CHESS;

DROP TABLE IF EXISTS accounts;
-- remove table if it already exists and start from scratch

CREATE TABLE accounts (
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  friends_list TEXT
);


