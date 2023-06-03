package com.mca.price.infrastructure.persistence;

import com.mca.price.domain.Brand;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BrandRepository extends ReactiveCrudRepository<Brand, Long> {

}
