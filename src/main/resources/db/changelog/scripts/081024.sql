--liquibase formatted sql
--changeset zhomartkhan.talgatuly:081024_1

CREATE SCHEMA adx;

--changeset zhomartkhan.talgatuly:081024_2

CREATE TABLE adx.air_sensor_signals (
    id BIGINT PRIMARY KEY
        GENERATED ALWAYS AS IDENTITY,
    create_date TIMESTAMP,
    lon DECIMAL,
    lat DECIMAL,
    pm_1_0 INT,
    pm_2_5 INT,
    pm_10 INT,
    aqi INT
);
