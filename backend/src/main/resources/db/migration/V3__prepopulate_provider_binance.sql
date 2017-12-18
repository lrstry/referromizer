INSERT INTO provider (id, name, url)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Binance',
    'https://www.binance.com/');