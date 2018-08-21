DROP USER gamestoredb CASCADE;

CREATE USER gamestoredb
IDENTIFIED BY  p4ssw0rd;

GRANT connect, resource, create session, create view, create table to gamestoredb;

conn gamestoredb/p4ssw0rd;

CREATE TABLE client (
    client_id   NUMBER NOT NULL,
    username    VARCHAR2(50) NOT NULL,
    fname       VARCHAR2(50),
    lname       VARCHAR2(50) NOT NULL,
    email       VARCHAR2(100) NOT NULL,
    password    VARCHAR2(50) NOT NULL
);
ALTER TABLE client ADD CONSTRAINT clients_pk PRIMARY KEY ( client_id );
CREATE SEQUENCE sq_client_pk START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER tr_insert_client
BEFORE INSERT ON client
FOR EACH ROW
BEGIN
    SELECT sq_client_pk.NEXTVAL INTO :NEW.client_id FROM DUAL;
END;
/

CREATE TABLE employee (
    employee_id   NUMBER NOT NULL,
    fname         VARCHAR2(50) NOT NULL,
    lname         VARCHAR2(50 CHAR) NOT NULL
);
ALTER TABLE employee ADD CONSTRAINT employees_pk PRIMARY KEY ( employee_id );
CREATE SEQUENCE sq_employee_pk START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER tr_insert_employee
BEFORE INSERT ON employee
FOR EACH ROW
BEGIN
    SELECT sq_employee_pk.NEXTVAL INTO :NEW.employee_id FROM DUAL;
END;
/

CREATE TABLE game (
    game_id   NUMBER NOT NULL,
    title     VARCHAR2(150) NOT NULL,
    price     VARCHAR2(100) NOT NULL,
    photo     VARCHAR2(100),
    copies    NUMBER NOT NULL
);
ALTER TABLE game ADD CONSTRAINT games_pk PRIMARY KEY ( game_id );
CREATE SEQUENCE sq_game_pk START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER tr_insert_game
BEFORE INSERT ON game
FOR EACH ROW
BEGIN
    SELECT sq_game_pk.NEXTVAL INTO :NEW.game_id FROM DUAL;
END;
/

CREATE TABLE manager (
    manager_id   NUMBER NOT NULL,
    username     VARCHAR2(50) NOT NULL,
    fname        VARCHAR2(50) NOT NULL,
    lname        VARCHAR2(50) NOT NULL,
    email        VARCHAR2(50) NOT NULL,
    password     VARCHAR2(50) NOT NULL
);
ALTER TABLE manager ADD CONSTRAINT managers_pk PRIMARY KEY ( manager_id );
CREATE SEQUENCE sq_manager_pk START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER tr_insert_manager
BEFORE INSERT ON manager
FOR EACH ROW
BEGIN
    SELECT sq_manager_pk.NEXTVAL INTO :NEW.manager_id FROM DUAL;
END;
/

CREATE TABLE report (
    report_id      NUMBER NOT NULL,
    title          VARCHAR2(100),
    body           VARCHAR2(500) NOT NULL,
    datereported   DATE NOT NULL,
    dateresolved   DATE NOT NULL,
    response       VARCHAR2(500),
    employee_id    NUMBER NOT NULL,
    manager_id     NUMBER NOT NULL,
    client_id      NUMBER NOT NULL
);
ALTER TABLE report ADD CONSTRAINT reports_pk PRIMARY KEY ( report_id );
CREATE SEQUENCE sq_report_pk START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER tr_insert_report
BEFORE INSERT ON report
FOR EACH ROW
BEGIN
    SELECT sq_report_pk.NEXTVAL INTO :NEW.manager_id FROM DUAL;
END;
/

CREATE TABLE request (
    request_id   NUMBER NOT NULL,
    title        VARCHAR2(100) NOT NULL,
    price        VARCHAR2(100) NOT NULL,
    photo        VARCHAR2(50),
    isapproved   NUMBER,
    client_id    NUMBER NOT NULL,
    manager_id   NUMBER NOT NULL
);
ALTER TABLE request ADD CONSTRAINT requests_pk PRIMARY KEY ( request_id );
CREATE SEQUENCE sq_request_pk START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER tr_insert_request
BEFORE INSERT ON request
FOR EACH ROW
BEGIN
    SELECT sq_request_pk.NEXTVAL INTO :NEW.request_id FROM DUAL;
END;
/

ALTER TABLE report
    ADD CONSTRAINT reports_clients_fk FOREIGN KEY ( client_id )
        REFERENCES client ( client_id );

ALTER TABLE report
    ADD CONSTRAINT reports_employees_fk FOREIGN KEY ( employee_id )
        REFERENCES employee ( employee_id );

ALTER TABLE report
    ADD CONSTRAINT reports_managers_fk FOREIGN KEY ( manager_id )
        REFERENCES manager ( manager_id );

ALTER TABLE request
    ADD CONSTRAINT requests_clients_fk FOREIGN KEY ( client_id )
        REFERENCES client ( client_id );

ALTER TABLE request
    ADD CONSTRAINT requests_managers_fk FOREIGN KEY ( manager_id )
        REFERENCES manager ( manager_id );