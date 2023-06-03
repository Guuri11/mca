package com.mca.price.infrastructure.persistence;

import com.mca.price.domain.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

}
