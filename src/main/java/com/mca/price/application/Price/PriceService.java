package com.mca.price.application.Price;

import com.mca.price.domain.mappers.PriceMapper;
import com.mca.price.infrastructure.persistence.PriceRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PriceService {

  private final PriceRepository priceRepository;
  private final PriceMapper priceMapper;

  PriceService(final PriceRepository priceRepository, final PriceMapper priceMapper) {

    this.priceRepository = priceRepository;
    this.priceMapper = priceMapper;
  }

  public Mono<PriceDto> getPrice(final LocalDateTime date, final Long productId, final Long brandId) {

    return priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductAndBrandOrderByPriorityDesc(
            date, date, productId, brandId)
        .map(this.priceMapper::toDto)
        .switchIfEmpty(Mono.error(new NotFoundException()));
  }
}
