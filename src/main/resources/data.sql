delete from price;
delete from product;
delete from brand;
delete from price_list;

insert into product (id, name) values
  (35455, 'Product 1');

insert into price_list (id, name) values
  (1, 'Price list 1'),
  (2, 'Price list 2'),
  (3, 'Price list 3'),
  (4, 'Price list 4');

insert into brand (id, name) values
  (1, 'Zara');

insert into price (id, brand_id, start_date, end_date, price_list_id, product_id, priority, amount, currency) values
(1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
(2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
(3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
(4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');


