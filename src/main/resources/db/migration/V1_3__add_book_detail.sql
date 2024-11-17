CREATE TABLE book_detail
(
        id int NOT NULL AUTO_INCREMENT,
        borrower_ID int NOT NULL,
        book_ID int NOT NULL,
        ver int,
        sts_cd varchar(1),
        created_dt timestamp,
        created_by VARCHAR(200),
        updated_dt timestamp,
        updated_by VARCHAR(200),
        PRIMARY KEY (ID),
        FOREIGN KEY (borrower_ID) REFERENCES borrower(id),
        FOREIGN KEY (book_ID) REFERENCES book(id)
)