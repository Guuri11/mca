create TABLE brand (
  id BIGINT PRIMARY KEY,
  name VARCHAR(255)
);

create TABLE price_list (
  id BIGINT PRIMARY KEY,
  name VARCHAR(255)
);

create TABLE product (
  id BIGINT PRIMARY KEY,
  name VARCHAR(255)
);

create TABLE price (
  id BIGINT PRIMARY KEY,
  brand_id BIGINT,
  startDate TIMESTAMP,
  endDate TIMESTAMP,
  priceList_id BIGINT,
  product_id BIGINT,
  priority INT,
  amount FLOAT,
  currency VARCHAR(10),
  FOREIGN KEY (brand_id) REFERENCES brand(id),
  FOREIGN KEY (priceList_id) REFERENCES price_list(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
);

insert into brand (id, name)
values (1, 'ZARA');

insert into price_list (id, name)
values (1, 'PriceList1'), (2, 'PriceList2'), (3, 'PriceList3'), (4, 'PriceList4');

insert into product (id, name)
values (35455, 'Product1');

insert into price (id, brand_id, startDate, endDate, priceList_id, product_id, priority, amount, currency)
values
(1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
(2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
(3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
(4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');

