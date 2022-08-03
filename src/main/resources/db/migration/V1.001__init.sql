CREATE TABLE IF NOT EXISTS public.person_resolution
(
    id bigint NOT NULL,
    errors integer,
    household_matches integer,
    individual_matches integer,
    non_matches integer,
    "timestamp" timestamp without time zone,
    url character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT person_resolution_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.batch_loader
(
    id bigint NOT NULL,
    file_created timestamp without time zone,
    file_picked_up timestamp without time zone,
    file_processed timestamp without time zone,
    filename character varying(255) COLLATE pg_catalog."default",
    number_records integer,
    "timestamp" timestamp without time zone,
    CONSTRAINT batch_loader_pkey PRIMARY KEY (id)
);