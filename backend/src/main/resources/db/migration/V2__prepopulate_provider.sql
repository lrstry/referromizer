INSERT INTO provider (id, name, url)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Coinbase',
    'https://www.coinbase.com/');