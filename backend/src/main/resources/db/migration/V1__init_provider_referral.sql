CREATE TABLE provider (
    id uuid NOT NULL,
    name character varying(255),
    url character varying(255)
);

CREATE TABLE referral (
    id uuid NOT NULL,
    hits integer,
    ref_id character varying(255),
    ref_url character varying(255),
    provider_id uuid
);

ALTER TABLE provider
    ADD CONSTRAINT provider_pkey PRIMARY KEY (id);

ALTER TABLE referral
    ADD CONSTRAINT referral_pkey PRIMARY KEY (id);

ALTER TABLE referral
    ADD CONSTRAINT fkey_provider_id FOREIGN KEY (provider_id) REFERENCES provider(id);