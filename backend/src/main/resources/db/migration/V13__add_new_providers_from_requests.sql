INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'COSS.IO',
    'https://coss.io/',
    '^[A-Z0-9]{10}$',
    E'^(?:http:\\/\\/|https:\\/\\/)(?:sso.coss.io\\/api\\/invite\\/)([A-Z0-9]{10})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='COSS';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Coinspot',
    'https://www.coinspot.com.au/',
    '^[A-Z0-9]{5}$',
    E'^(?:http:\\/\\/www.|https:\\/\\/www.|www.{1})(?:coinspot.com.au\\/\\?affiliate=)([A-Z0-9]{5})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Coinspot';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Livecoin',
    'https://www.livecoin.net/',
    '^Livecoin-[A-Za-z0-9]{8}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:livecoin.net\\/\\?from=)(Livecoin-[A-Za-z0-9]{8})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Livecoin';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'QuadrigaCX',
    'https://www.quadrigacx.com/',
    '^[a-z0-9]{24}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:quadrigacx.com\\/\\?ref=)([a-z0-9]{24})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Quadrigacx';

INSERT INTO provider (id, name, url, ref_id_regex, ref_url_regex)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'BitMEX',
    'https://www.bitmex.com/',
    '^[a-zA-Z0-9]{6}$',
    E'^(?:http:\\/\\/|https:\\/\\/){0,1}(?:www.){0,1}(?:bitmex.com\\/register\\/)([a-zA-Z0-9]{6})$');

UPDATE provider_request SET status='ACTIVE' WHERE name='Bitmex';