INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'altcoin.io',
    'https://www.altcoin.io/',
    '^[A-Z0-9]{5}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:altcoin.io\\?kid=)([A-Z0-9]{5})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Altcoin.io';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'LiteBit',
    'https://www.litebit.eu/',
    '^[0-9]{6}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:litebit.eu\\?referrer=)([0-9]{6})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Litebit';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Robinhood',
    'https://robinhood.com/',
    '^[a-zA-Z0-9]+$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:share.robinhood.com\\/)([a-zA-Z0-9]+)$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Robinhood';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'LocalBitcoins',
    'https://localbitcoins.com/',
    '^[a-z0-9]{4}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:localbitcoins.com\\/\\?ch=)([a-z0-9]{4})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='LocalBitcoins';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Changer',
    'https://www.changer.com/',
    '^[a-zA-Z0-9]+$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:changer.com\\/\\?ref=)([a-zA-Z0-9]+)$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Changer';