ALTER TABLE AD_PARTICIPANTS RENAME TO ORDERS;
RENAME AD_PARTICIPANTS_SEQ TO ORDERS_SEQ;

ALTER TABLE ORDERS
DROP CONSTRAINT AD_ID_FK;

ALTER TABLE ORDERS
DROP CONSTRAINT USER_ID_FK;

ALTER TABLE ORDERS
ADD ID NUMBER;

ALTER TABLE ORDERS
DROP PRIMARY KEY;

ALTER TABLE ORDERS
DROP COLUMN ID;

ALTER TABLE ORDERS
ADD ID NUMBER NOT NULL;

ALTER TABLE ORDERS
RENAME COLUMN AD_ID TO ID;

ALTER TABLE ORDERS
ADD AD_ID NUMBER NOT NULL;

ALTER TABLE ORDERS RENAME TO OLD_ORDERS;

CREATE TABLE ORDERS(
ID NUMBER NOT NULL,
PRIMARY KEY(ID),
AD_ID NUMBER NOT NULL,
CONSTRAINT AD_ID_FK FOREIGN KEY(AD_ID) REFERENCES AD(ID),
USER_ID NUMBER NOT NULL,
CONSTRAINT USER_ID_FK FOREIGN KEY(USER_ID) REFERENCES USERS(ID),
ITEM_COUNT NUMBER NOT NULL,
DATE_CREATED TIMESTAMP NOT NULL
)

insert into ORDERS (AD_ID, USER_ID) select ID, USER_ID from OLD_ORDERS;

DROP TABLE OLD_ORDERS;

ALTER TABLE ORDERS
RENAME COLUMN ITEM_COUNT TO ITEMS_COUNT;