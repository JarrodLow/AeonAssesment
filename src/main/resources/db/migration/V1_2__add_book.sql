CREATE TABLE book
(
        id int NOT NULL AUTO_INCREMENT,
        ISBN VARCHAR(200),
        title VARCHAR(200),
        author VARCHAR(200),
        ver int,
        sts_cd varchar(1),
        created_dt timestamp,
        created_by VARCHAR(200),
        updated_dt timestamp,
        updated_by VARCHAR(200),
        PRIMARY KEY (ID)
)