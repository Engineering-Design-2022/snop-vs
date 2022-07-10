CREATE USER docker;
CREATE DATABASE docker;
GRANT ALL PRIVILEGES ON DATABASE docker TO docker;
\c docker

CREATE TABLE book (
  id integer,
  name varchar(30)
);
--テーブルにデータを挿入
INSERT INTO book VALUES (1,'SQL book');