CREATE TABLE clients (
    client_id   NUMBER NOT NULL,
    username    VARCHAR2(50) NOT NULL,
    fname       VARCHAR2(50),
    lname       VARCHAR2(50) NOT NULL,
    email       VARCHAR2(100) NOT NULL,
    password    VARCHAR2(50) NOT NULL
);

ALTER TABLE clients ADD CONSTRAINT clients_pk PRIMARY KEY ( client_id );

CREATE TABLE employees (
    employee_id   NUMBER NOT NULL,
    fname         VARCHAR2(50) NOT NULL,
    lname         VARCHAR2(50 CHAR) NOT NULL
);

ALTER TABLE employees ADD CONSTRAINT employees_pk PRIMARY KEY ( employee_id );

CREATE TABLE games (
    game_id   NUMBER NOT NULL,
    title     VARCHAR2(150) NOT NULL,
    price     VARCHAR2(100) NOT NULL,
    photo     VARCHAR2(100),
    copies    NUMBER NOT NULL
);

ALTER TABLE games ADD CONSTRAINT games_pk PRIMARY KEY ( game_id );

CREATE TABLE managers (
    manager_id   NUMBER NOT NULL,
    username     VARCHAR2(50) NOT NULL,
    fname        VARCHAR2(50) NOT NULL,
    lname        VARCHAR2(50) NOT NULL,
    email        VARCHAR2(50) NOT NULL,
    password     VARCHAR2(50) NOT NULL
);

ALTER TABLE managers ADD CONSTRAINT managers_pk PRIMARY KEY ( manager_id );

CREATE TABLE reports (
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

ALTER TABLE reports ADD CONSTRAINT reports_pk PRIMARY KEY ( report_id );

CREATE TABLE requests (
    request_id   NUMBER NOT NULL,
    title        VARCHAR2(100) NOT NULL,
    price        VARCHAR2(10000) NOT NULL,
    photo        VARCHAR2(50),
    isapproved   NUMBER,
    client_id    NUMBER NOT NULL,
    manager_id   NUMBER NOT NULL
);

ALTER TABLE requests ADD CONSTRAINT requests_pk PRIMARY KEY ( request_id );

ALTER TABLE reports
    ADD CONSTRAINT reports_clients_fk FOREIGN KEY ( client_id )
        REFERENCES clients ( client_id );

ALTER TABLE reports
    ADD CONSTRAINT reports_employees_fk FOREIGN KEY ( employee_id )
        REFERENCES employees ( employee_id );

ALTER TABLE reports
    ADD CONSTRAINT reports_managers_fk FOREIGN KEY ( manager_id )
        REFERENCES managers ( manager_id );

ALTER TABLE requests
    ADD CONSTRAINT requests_clients_fk FOREIGN KEY ( client_id )
        REFERENCES clients ( client_id );

ALTER TABLE requests
    ADD CONSTRAINT requests_managers_fk FOREIGN KEY ( manager_id )
        REFERENCES managers ( manager_id );