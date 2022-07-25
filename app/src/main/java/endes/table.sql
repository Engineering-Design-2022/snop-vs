-- 一旦、既存のテーブルを削除する
DROP TABLE NumberOfPeople;
DROP TABLE Room;

-- Roomテーブルを作成する
CREATE TABLE Room (
  Id INTEGER PRIMARY KEY,
  Name VARCHAR(128),
  Description VARCHAR(256)
);

-- NumberOfPeopleテーブルを作成する
CREATE TABLE NumberOfPeople (
  Id INTEGER PRIMARY KEY,
  Number INTEGER,
  CreatedAt DATETIME,
  RoomId INTEGER,
  FOREIGN KEY (RoomId) REFERENCES Room (Id)
);