package com.mca.price.application.Price;

import com.mca.price.domain.mappers.PriceMapper;
import com.mca.price.infrastructure.persistence.BrandRepository;
import com.mca.price.infrastructure.persistence.PriceRepository;
import com.mca.price.infrastructure.persistence.ProductRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PriceService {

  private final PriceRepository priceRepository;
  private final PriceMapper priceMapper;
  private final ProductRepository productRepository;
  private final BrandRepository brandRepository;

  PriceService(final PriceRepository priceRepository, final PriceMapper priceMapper,
      final ProductRepository productRepository,
      final BrandRepository brandRepository) {

    this.priceRepository = priceRepository;
    this.priceMapper = priceMapper;
    this.productRepository = productRepository;
    this.brandRepository = brandRepository;
  }

  public Mono<PriceDto> getPrice(final LocalDateTime date, final Long productId, final Long brandId) {

    return priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
            date, date, getProductId(productId), getBrandId(brandId))
        .map(this.priceMapper::toDto)
        .switchIfEmpty(Mono.error(new NotFoundException()));
  }

  private Long getProductId(final Long productId) {

    return Objects.requireNonNull(productRepository.findById(productId)
            .switchIfEmpty(Mono.error(new NotFoundException()))
            .block())
        .getId();
  }

  private Long getBrandId(final Long brandId) {

    return Objects.requireNonNull(brandRepository.findById(brandId)
            .switchIfEmpty(Mono.error(new NotFoundException()))
            .block())
        .getId();
  }
}
