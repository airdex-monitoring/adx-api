--liquibase formatted sql

--changeset zhomartkhan.talgatuly:141024_1

CREATE TABLE adx.map_sectors (
    id BIGINT PRIMARY KEY
        GENERATED ALWAYS AS IDENTITY,
    create_date TIMESTAMP,
    code VARCHAR(16) UNIQUE,
    points TEXT
);

CREATE INDEX IF NOT EXISTS idx_code_map_sectors
    ON adx.map_sectors(code);

--changeset zhomartkhan.talgatuly:141024_2

ALTER TABLE adx.air_sensor_signals
    ADD COLUMN sector_id BIGINT;

ALTER TABLE adx.air_sensor_signals
    ADD CONSTRAINT fk_sector_id_map_sectors
        FOREIGN KEY (sector_id) REFERENCES adx.map_sectors (id);

CREATE INDEX IF NOT EXISTS idx_sector_id_air_sensor_signals
    ON adx.air_sensor_signals (sector_id);

--changeset zhomartkhan.talgatuly:141024_3

INSERT INTO adx.map_sectors (code, points) VALUES ('A1', '[{"lat": 51.097297, "lon": 71.414138}, {"lat": 51.093427, "lon": 71.412509}, {"lat": 51.092003, "lon": 71.423560}, {"lat": 51.095740, "lon": 71.424906}]');
INSERT INTO adx.map_sectors (code, points) VALUES ('A2', '[{"lat": 51.093427, "lon": 71.412509}, {"lat": 51.087019, "lon": 71.409959}, {"lat": 51.085373, "lon": 71.421010}, {"lat": 51.092003, "lon": 71.423560}]');
INSERT INTO adx.map_sectors (code, points) VALUES ('B1', '[{"lat": 51.099655, "lon": 71.426465}, {"lat": 51.098899, "lon": 71.431778}, {"lat": 51.094951, "lon": 71.430486}, {"lat": 51.095740, "lon": 71.424906}]');
INSERT INTO adx.map_sectors (code, points) VALUES ('B2', '[{"lat": 51.092003, "lon": 71.423560}, {"lat": 51.095740, "lon": 71.424906}, {"lat": 51.094951, "lon": 71.430486}, {"lat": 51.091291, "lon": 71.428944}]');
INSERT INTO adx.map_sectors (code, points) VALUES ('B3', '[{"lat": 51.092003, "lon": 71.423560}, {"lat": 51.088176, "lon": 71.422088}, {"lat": 51.087286, "lon": 71.427740}, {"lat": 51.091291, "lon": 71.428944}]');
INSERT INTO adx.map_sectors (code, points) VALUES ('B4', '[{"lat": 51.088176, "lon": 71.422088}, {"lat": 51.085373, "lon": 71.421010}, {"lat": 51.084438, "lon": 71.426748}, {"lat": 51.087286, "lon": 71.427740}]');
INSERT INTO adx.map_sectors (code, points) VALUES ('C1', '[{"lat": 51.098899, "lon": 71.431778}, {"lat": 51.098009, "lon": 71.437728}, {"lat": 51.094227, "lon": 71.436241}, {"lat": 51.094951, "lon": 71.430486}]');
INSERT INTO adx.map_sectors (code, points) VALUES ('C2', '[{"lat": 51.094951, "lon": 71.430486}, {"lat": 51.094227, "lon": 71.436241}, {"lat": 51.090446, "lon": 71.434824}, {"lat": 51.091291, "lon": 71.428944}]');
INSERT INTO adx.map_sectors (code, points) VALUES ('C3', '[{"lat": 51.091291, "lon": 71.428944}, {"lat": 51.090446, "lon": 71.434824}, {"lat": 51.086574, "lon": 71.433407}, {"lat": 51.087286, "lon": 71.427740}]');
INSERT INTO adx.map_sectors (code, points) VALUES ('C4', '[{"lat": 51.087286, "lon": 71.427740}, {"lat": 51.084438, "lon": 71.426748}, {"lat": 51.083726, "lon": 71.432344}, {"lat": 51.086574, "lon": 71.433407}]');
INSERT INTO adx.map_sectors (code, points) VALUES ('D1', '[{"lat": 51.098009, "lon": 71.437728}, {"lat": 51.097119, "lon": 71.444246}, {"lat": 51.093249, "lon": 71.442900}, {"lat": 51.094227, "lon": 71.436241}]');
INSERT INTO adx.map_sectors (code, points) VALUES ('D2', '[{"lat": 51.094227, "lon": 71.436241}, {"lat": 51.090446, "lon": 71.434824}, {"lat": 51.089553, "lon": 71.441352}, {"lat": 51.093249, "lon": 71.442900}]');
