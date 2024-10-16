--liquibase formatted sql

--changeset zhomartkhan.talgatuly:161024_1

CREATE INDEX IF NOT EXISTS idx_create_date_air_sensor_signals
    ON adx.air_sensor_signals(create_date);
