create TABLE IF NOT EXISTS product (
  id BIGINT PRIMARY KEY,
  name VARCHAR(255)
);

create TABLE IF NOT EXISTS brand (
  id BIGINT PRIMARY KEY,
  name VARCHAR(255)
);

create TABLE IF NOT EXISTS price (
  id BIGINT PRIMARY KEY,
  brand_id BIGINT,
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  price_list BIGINT,
  product_id BIGINT,
  priority INT,
  amount FLOAT,
  currency VARCHAR(50),
  FOREIGN KEY (brand_id) REFERENCES brand (id),
  FOREIGN KEY (product_id) REFERENCES product (id)
);
