package com.mca.price.application.Price;

import com.mca.price.domain.Brand;
import com.mca.price.domain.Product;
import com.mca.price.domain.mappers.PriceMapper;
import com.mca.price.infrastructure.persistence.BrandRepository;
import com.mca.price.infrastructure.persistence.PriceRepository;
import com.mca.price.infrastructure.persistence.ProductRepository;
import java.time.LocalDateTime;
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

    return Mono.zip(
            getProductId(productId),
            getBrandId(brandId)
        )
        .flatMap(tuple -> {
          final Long resolvedProductId = tuple.getT1();
          final Long resolvedBrandId = tuple.getT2();
          return priceRepository
              .findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                  date, date, resolvedProductId, resolvedBrandId
              )
              .map(priceMapper::toDto)
              .switchIfEmpty(Mono.error(new NotFoundException()));
        });
  }

  private Mono<Long> getProductId(final Long productId) {

    return productRepository.findById(productId)
        .switchIfEmpty(Mono.error(new NotFoundException()))
        .map(Product::getId);
  }

  private Mono<Long> getBrandId(final Long brandId) {

    return brandRepository.findById(brandId)
        .switchIfEmpty(Mono.error(new NotFoundException()))
        .map(Brand::getId);
  }

}
