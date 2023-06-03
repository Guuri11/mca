package com.mca.price.infrastructure.persistence;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mca.price.domain.Brand;
import com.mca.price.domain.Currency;
import com.mca.price.domain.Price;
import com.mca.price.domain.Product;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class PriceRepositoryTest {

  @Mock
  private PriceRepository priceRepository;

  @Test
  void findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId_ShouldReturnPrice() {
    // Given
    final LocalDateTime date = LocalDateTime.now();
    final Brand brand = new Brand(1L, "Example Brand");
    final Product product = new Product(1L, "Example Product");

    final Price expectedPrice = new Price(1L, brand, date, date.plusDays(1), 1L, product, 1, 10, Currency.EUR);

    when(
        priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductAndBrandOrderByPriorityDesc(
            date, date,
            product.getId(),
            brand.getId()))
        .thenReturn(Mono.just(expectedPrice));

    // When
    final Mono<Price> result = priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductAndBrandOrderByPriorityDesc(
        date, date,
        product.getId(),
        brand.getId());
    // Then
    StepVerifier.create(result)
        .expectNext(expectedPrice)
        .verifyComplete();

    verify(priceRepository,
        times(1)).findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductAndBrandOrderByPriorityDesc(
        date, date,
        brand.getId(), product.getId());
  }

  @Test
  void findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId_ShouldReturnNull() {
    // Given
    final LocalDateTime date = LocalDateTime.now();
    final Brand brand = new Brand(1L, "Example Brand");
    final Product product = new Product(1L, "Example Product");

    when(
        priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductAndBrandOrderByPriorityDesc(
            date, date,
            product.getId(),
            brand.getId()))
        .thenReturn(Mono.empty());

    // When
    final Mono<Price> result = priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductAndBrandOrderByPriorityDesc(
        date, date,
        product.getId(), brand.getId());

    // Then
    StepVerifier.create(result)
        .verifyComplete();

    verify(priceRepository,
        times(1)).findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductAndBrandOrderByPriorityDesc(
        date, date,
        brand.getId(), product.getId());
  }
}
