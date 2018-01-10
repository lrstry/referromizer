CREATE TABLE provider_request (
    id uuid NOT NULL,
    example_referral_url character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    status character varying(10),
    upvotes integer
);

ALTER TABLE ONLY provider_request
    ADD CONSTRAINT provider_request_pkey PRIMARY KEY (id);