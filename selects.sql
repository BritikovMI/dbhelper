DROP TABLE BRM_ORDER_ITEM;
DROP TABLE BRM_ORDER;
DROP TABLE BRM_PRODUCT;
DROP TABLE BRM_CUSTOMER;

------------------BRM_CUSTOMER------------------
CREATE TABLE BRM_CUSTOMER(
    ID_PK NUMBER(38)              NOT NULL,
    NM VARCHAR2(255)              NOT NULL,
    CONSTRAINT PK_BRM_CUSTOMER_ID_PK PRIMARY KEY (ID_PK)
);

INSERT INTO BRM_CUSTOMER (ID_PK, NM)
    VALUES (1, 'Иванов Илья Д. (External)');
INSERT INTO BRM_CUSTOMER (ID_PK, NM)
    VALUES (2, 'Петров Василий Л. (External)');
INSERT INTO BRM_CUSTOMER (ID_PK, NM)
    VALUES (3, 'Николаев Иван И. (External)');

------------------BRM_PRODUCT------------------
CREATE TABLE BRM_PRODUCT(
    ID_PK NUMBER(38)              NOT NULL,
    PRODUCT_TYPE VARCHAR2(255)    NOT NULL,
    CONSTRAINT PK_BRM_PRODUCT_ID_PK PRIMARY KEY (ID_PK)
);

INSERT INTO BRM_PRODUCT (ID_PK, PRODUCT_TYPE)
VALUES (1, 'Смартфон');
INSERT INTO BRM_PRODUCT (ID_PK, PRODUCT_TYPE)
VALUES (2, 'ПК');
INSERT INTO BRM_PRODUCT (ID_PK, PRODUCT_TYPE)
VALUES (3, 'Ноутбук');

-------------------BRM_ORDER-------------------
CREATE TABLE BRM_ORDER(
    ID_PK NUMBER(38)              NOT NULL,
    DATE_OF VARCHAR2(255)         NOT NULL,
    CUSTOMER_ID NUMBER(38)        NOT NULL,
    CONSTRAINT PK_BRM_ORDER_ID_PK PRIMARY KEY (ID_PK),
    CONSTRAINT FK_BRM_ORDER_CUSTOMER_ID FOREIGN KEY (CUSTOMER_ID) REFERENCES BRM_CUSTOMER (ID_PK)
);

INSERT INTO BRM_ORDER (ID_PK, DATE_OF, CUSTOMER_ID)
VALUES (1, '22.06.2017', 1);
INSERT INTO BRM_ORDER (ID_PK, DATE_OF, CUSTOMER_ID)
VALUES (2, '12.07.2017', 1);
INSERT INTO BRM_ORDER (ID_PK, DATE_OF, CUSTOMER_ID)
VALUES (3, '14.07.2017', 2);
INSERT INTO BRM_ORDER (ID_PK, DATE_OF, CUSTOMER_ID)
VALUES (4, '18.09.2017', 3);


-------------------BRM_ORDER_ITEM-------------------
CREATE TABLE BRM_ORDER_ITEM(
    ORDER_ITEM_ID NUMBER(38)     NOT NULL,
    ORDER_ID NUMBER(38)          NOT NULL,
    PRODUCT_ID NUMBER(38)        NOT NULL,
    CONSTRAINT PK_BRM_ORDER_ITEM_ID_PK PRIMARY KEY (ORDER_ITEM_ID),
    CONSTRAINT FK_BRM_ORDER_ITEM_ORDER_ID FOREIGN KEY (ORDER_ID) REFERENCES BRM_ORDER (ID_PK),
    CONSTRAINT FK_BRM_ORDER_ITEM_PRODUCT_ID FOREIGN KEY (PRODUCT_ID) REFERENCES BRM_PRODUCT (ID_PK)
);

INSERT INTO BRM_ORDER_ITEM (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID)
VALUES (1, 1, 1);
INSERT INTO BRM_ORDER_ITEM (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID)
VALUES (2, 1, 2);
INSERT INTO BRM_ORDER_ITEM (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID)
VALUES (3, 2, 3);
INSERT INTO BRM_ORDER_ITEM (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID)
VALUES (4, 3, 1);
INSERT INTO BRM_ORDER_ITEM (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID)
VALUES (5, 4, 2);
INSERT INTO BRM_ORDER_ITEM (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID)
VALUES (6, 4, 2);


-- ------------------------------------------------------------------------------------
-- FIN
-- ------------------------------------------------------------------------------------
commit;



-----------------------------SELECTS-----------------------------------------

SELECT * FROM BRM_ORDER;                                 --Выбираем все столбцы из базы
SELECT ID_PK, CUSTOMER_ID FROM BRM_ORDER;                --Выбираем только определенные столбцы
SELECT DATE_OF FROM BRM_ORDER WHERE ID_PK != '2';        --Выбираем  определенную ячейку
SELECT DATE_OF FROM BRM_ORDER WHERE ID_PK = 2 OR ID_PK = 4;         --Селекты с логикой
SELECT DATE_OF FROM BRM_ORDER WHERE ID_PK = 2 AND CUSTOMER_ID = 1;
SELECT * FROM BRM_ORDER WHERE ID_PK IN (1, 1);           --Использование IN Для объединения ячеек в выборке
SELECT * FROM BRM_ORDER ORDER BY ID_PK DESC;             --Получаем результат в упорядоченном виде, от большего к меньшему
SELECT * FROM BRM_ORDER ORDER BY ID_PK ASC;              --Получаем результат в упорядоченном виде, от меньшего к большему
SELECT DATE_OF FROM BRM_ORDER WHERE DATE_OF LIKE '%2.07%'; --Находит ячейку, чье содержимое включает в себя искомую строку
UPDATE BRM_ORDER SET CUSTOMER_ID = '1',ID_PK = '2' WHERE DATE_OF = '12.07.2017'; --Изменяем содержимое в таблице, с помощью WHERE выбираем только определенный столбец
SELECT * FROM BRM_ORDER;                                    --Просматриваем изменения