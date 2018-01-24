DELETE FROM wallet WHERE wallet.name = 'Litecoin';
DELETE FROM wallet WHERE wallet.name = 'Bitcoin';

UPDATE wallet
    SET address='0xFC5cAAED00B61333F11Db736924673F41249f71A'
    WHERE name='Ethereum';