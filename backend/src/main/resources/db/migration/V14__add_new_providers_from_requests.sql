INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'YoBit',
    'https://yobit.io/',
    '^[a-zA-Z]{5}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:yobit.io\\/\\?bonus=)([a-zA-Z]{5})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Yobit';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'HitBTC',
    'https://hitbtc.com/',
    '^[a-z0-9]{13}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:hitbtc.com\\/\\?ref_id=)([a-z0-9]{13})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Hitbtc';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Bit-Z',
    'https://www.bit-z.com/',
    '^[0-9]{7}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:bit\\-z.com\\/user\\/signup\\?pid=)([0-9]{7})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Bitz';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'CoinPayments',
    'https://www.coinpayments.net/',
    '^[a-z0-9]{32}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:coinpayments.net\\/index.php\\?ref=)([a-z0-9]{32})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Coinpayments';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Ledger Wallet',
    'https://www.ledgerwallet.com/',
    '^[a-z0-9]{4}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:ledgerwallet.com\\/r\\/)([a-z0-9]{4})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Ledgerwallet';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Changelly',
    'https://changelly.com/',
    '^[a-z0-9]{12}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:changelly.com\\/\\?ref_id=)([a-z0-9]{12})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Changelly';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'MinerGate',
    'https://minergate.com/',
    '^[a-z0-9]{24}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:[a-z]{2}.){0,1}(?:minergate.com\\/a\\/)([a-z0-9]{24})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Minergate';