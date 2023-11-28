CREATE SEQUENCE IF NOT EXISTS brand_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS car_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS maintenance_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS model_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS reservation_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS user_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE brand
(
    id         INTEGER NOT NULL,
    brand_name VARCHAR(255),
    CONSTRAINT pk_brand PRIMARY KEY (id)
);

CREATE TABLE car
(
    id                 INTEGER NOT NULL,
    body_type          VARCHAR(255),
    price_per_day      FLOAT,
    car_category       VARCHAR(255),
    seats              INTEGER,
    horse_power        INTEGER,
    fuel_type          VARCHAR(255),
    year_of_production INTEGER,
    mileage            INTEGER,
    is_available       BOOLEAN,
    localization       SMALLINT,
    CONSTRAINT pk_car PRIMARY KEY (id)
);

CREATE TABLE maintenance
(
    id     INTEGER NOT NULL,
    car_id INTEGER,
    CONSTRAINT pk_maintenance PRIMARY KEY (id)
);

CREATE TABLE model
(
    id         INTEGER NOT NULL,
    model_name VARCHAR(255),
    brand_id   INTEGER,
    CONSTRAINT pk_model PRIMARY KEY (id)
);

CREATE TABLE reservation
(
    id             INTEGER NOT NULL,
    user_id        INTEGER,
    car_id         INTEGER,
    start_date     date,
    end_date       date,
    insurance_type SMALLINT,
    total_price    FLOAT   NOT NULL,
    status         SMALLINT,
    CONSTRAINT pk_reservation PRIMARY KEY (id)
);

CREATE TABLE "user"
(
    id           INTEGER NOT NULL,
    first_name   VARCHAR(255),
    second_name  VARCHAR(255),
    is_verified  BOOLEAN NOT NULL,
    email        VARCHAR(255),
    phone_number VARCHAR(255),
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE maintenance
    ADD CONSTRAINT FK_MAINTENANCE_ON_CAR FOREIGN KEY (car_id) REFERENCES car (id);

ALTER TABLE model
    ADD CONSTRAINT FK_MODEL_ON_BRAND FOREIGN KEY (brand_id) REFERENCES brand (id);

ALTER TABLE reservation
    ADD CONSTRAINT FK_RESERVATION_ON_CAR FOREIGN KEY (car_id) REFERENCES car (id);

ALTER TABLE reservation
    ADD CONSTRAINT FK_RESERVATION_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);