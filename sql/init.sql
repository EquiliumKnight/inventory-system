CREATE TABLE "products" (
  "id" bigserial PRIMARY KEY,
  "name" varchar,
  "description" varchar,
  "category" varchar,
  "price" decimal,
  "sku" varchar
);

CREATE TABLE "inventary" (
  "id" bigserial PRIMARY KEY,
  "product_id" bigint,
  "store_id" varchar,
  "quantity" integer,
  "min_stock" integer
);

CREATE TABLE "moves" (
  "id" bigserial PRIMARY KEY,
  "product_id" bigint,
  "source_store_id" varchar,
  "target_store_id" varchar,
  "quantity" integer,
  "moved_at" timestamp,
  "type" varchar default ''
);

ALTER TABLE "inventary" ADD FOREIGN KEY ("product_id") REFERENCES "products" ("id");

ALTER TABLE "moves" ADD FOREIGN KEY ("product_id") REFERENCES "products" ("id");