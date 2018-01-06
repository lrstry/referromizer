ALTER TABLE provider
    ADD COLUMN ref_id_regex character varying(255),
    ADD COLUMN ref_url_regex character varying(255);

UPDATE provider
    SET ref_id_regex='^[0-9]{8}$', ref_url_regex= E'^(?:http|https{1})(?:\\:\\/\\/www.binance.com\\/\\?ref=)([0-9]{8})$'
    WHERE name='Binance';
UPDATE provider
    SET ref_id_regex='^[a-z0-9]{24}$', ref_url_regex= E'^(?:http|https{1})(?:\\:\\/\\/www.coinbase.com\\/join\\/)([a-z0-9]{24})$'
    WHERE name='Coinbase';