--liquibase formatted sql
--changeset zhomartkhan.talgatuly:091024_1

ALTER TABLE adx.air_sensor_signals
    ALTER COLUMN aqi TYPE DECIMAL;

--changeset zhomartkhan.talgatuly:091024_2

ALTER TABLE adx.air_sensor_signals
    ADD COLUMN aqi_level VARCHAR(32);
