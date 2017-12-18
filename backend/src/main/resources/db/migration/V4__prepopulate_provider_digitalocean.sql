INSERT INTO provider (id, name, url)
VALUES (
    md5(random()::text || clock_timestamp()::text)::uuid,
    'Digital Ocean',
    'https://www.digitalocean.com/');