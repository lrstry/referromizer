CREATE TABLE wallet (
    id uuid NOT NULL,
    name character varying(255),
    address character varying(255)
);

INSERT INTO wallet (id, name, address)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Bitcoin',
    '14s92NUE33bNqQvPCxENQ5P3HCuKsp7i4F');
INSERT INTO wallet (id, name, address)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Litecoin',
    'LKGwUruF9ayNXKPBsPUVmaEeUSvWFJeduy');
INSERT INTO wallet (id, name, address)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Ethereum',
    '0x9769e71BbfB2D9b13B18c43bE2e8E03e470c705b');