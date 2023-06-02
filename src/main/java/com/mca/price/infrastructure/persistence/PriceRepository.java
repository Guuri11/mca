package com.mca.price.infrastructure.persistence;

import com.mca.price.domain.Brand;
import com.mca.price.domain.Price;
import com.mca.price.domain.Product;
import java.time.LocalDate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PriceRepository extends ReactiveCrudRepository<Price, Long> {

  Mono<Price> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandAndProduct(
      LocalDate startDate, LocalDate endDate, Brand brand, Product product);
}
