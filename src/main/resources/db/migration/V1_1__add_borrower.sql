CREATE TABLE borrower
(
        id int NOT NULL AUTO_INCREMENT,
        name VARCHAR(200),
        email VARCHAR(200),
        ver int,
        sts_cd varchar(1),
        created_dt timestamp,
        created_by VARCHAR(200),
        updated_dt timestamp,
        updated_by VARCHAR(200),
        PRIMARY KEY (ID)
)