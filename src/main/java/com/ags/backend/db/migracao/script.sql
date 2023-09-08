create schema if not exists ags;

CREATE TABLE IF NOT EXISTS ags.brand
(
    id_brand integer NOT NULL DEFAULT nextval('ags.brand_id_brand_seq'::regclass),
    name_brand character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT brand_pkey PRIMARY KEY (id_brand)
)

CREATE TABLE IF NOT EXISTS ags.fuel
(
    id_fuel integer NOT NULL DEFAULT nextval('ags.fuel_id_fuel_seq'::regclass),
    name_fuel character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT fuel_pkey PRIMARY KEY (id_fuel)
)

CREATE TABLE IF NOT EXISTS ags.model
(
    id_model integer NOT NULL DEFAULT nextval('ags.model_id_model_seq'::regclass),
    name_model character varying(100) COLLATE pg_catalog."default",
    brand_id integer,
    CONSTRAINT model_pkey PRIMARY KEY (id_model),
    CONSTRAINT fkhbgv4j3vpt308sepyq9q79mhu FOREIGN KEY (brand_id)
        REFERENCES ags.brand (id_brand) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS ags.vehicle
(
    id_vehicle integer NOT NULL DEFAULT nextval('ags.vehicle_id_vehicle_seq'::regclass),
    price_vehicle real,
    tract_vehicle character varying(3) COLLATE pg_catalog."default",
    trans_vehicle character varying(3) COLLATE pg_catalog."default",
    usage_vehicle integer,
    fuel_id integer,
    model_id integer,
    CONSTRAINT vehicle_pkey PRIMARY KEY (id_vehicle),
    CONSTRAINT fk27cc9wpp4hrwtmjyew3ln1pw3 FOREIGN KEY (model_id)
        REFERENCES ags.model (id_model) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkr1mis2s6r2r52w8g7x82l9515 FOREIGN KEY (fuel_id)
        REFERENCES ags.fuel (id_fuel) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS ags.vehicle
    OWNER to postgres;