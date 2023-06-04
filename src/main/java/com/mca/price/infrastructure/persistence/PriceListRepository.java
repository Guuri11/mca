package com.mca.price.infrastructure.persistence;

import com.mca.price.domain.PriceList;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PriceListRepository extends ReactiveCrudRepository<PriceList, Long> {

}
