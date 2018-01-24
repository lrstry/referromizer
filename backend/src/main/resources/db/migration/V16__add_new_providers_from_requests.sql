INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Tokia',
    'https://www.tokia.io/',
    '^[a-z0-9]{8}-(?:[a-z0-9]{4}-){3}[a-z0-9]{12}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:wallet.tokia.io\\/Referal\\/)([a-z0-9]{8}-(?:[a-z0-9]{4}-){3}[a-z0-9]{12})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Tokia';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Cointracking',
    'https://cointracking.info/',
    '^[A-Z0-9]{7}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:cointracking.info\\?ref=)([A-Z0-9]{7})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Cointracking';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Perfectmoney',
    'https://perfectmoney.is/',
    '^[0-9]{7}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:perfectmoney.is\\/\\?ref=)([0-9]{7})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Perfectmoney';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Coinsquare',
    'https://coinsquare.io/',
    '^[A-Z0-9]{9}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:coinsquare.io\\/register\\?r=)([A-Z0-9]{9})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='coinsquare';