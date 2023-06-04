package com.mca.price.infrastructure.persistence;

import com.mca.price.domain.Price;
import java.time.LocalDateTime;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PriceRepository extends ReactiveCrudRepository<Price, Long> {

  Mono<Price> findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
      LocalDateTime startDate,
      LocalDateTime endDate,
      Long productId,
      Long brandId);
}
