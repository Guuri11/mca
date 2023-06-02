package com.mca.price.infrastructure.persistence;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mca.price.domain.Brand;
import com.mca.price.domain.Currency;
import com.mca.price.domain.Price;
import com.mca.price.domain.PriceList;
import com.mca.price.domain.Product;
import java.time.LocalDate;
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
  void findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandAndProduct_ShouldReturnPrice() {
    // Given
    final LocalDate date = LocalDate.now();
    final Brand brand = new Brand(1L, "Example Brand");
    final Product product = new Product(1L, "Example Product");
    final PriceList priceList = new PriceList(1L, "Example Price List");

    final Price expectedPrice = new Price(1L, brand, date, date.plusDays(1), priceList, product, 1, 10, Currency.EUR);

    when(priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandAndProduct(date, date, brand,
        product))
        .thenReturn(Mono.just(expectedPrice));

    // When
    final Mono<Price> result = priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandAndProduct(
        date, date,
        brand, product);

    // Then
    StepVerifier.create(result)
        .expectNext(expectedPrice)
        .verifyComplete();

    verify(priceRepository, times(1)).findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandAndProduct(date,
        date,
        brand, product);
  }

  @Test
  void findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandAndProduct_ShouldReturnNull() {
    // Given
    final LocalDate date = LocalDate.now();
    final Brand brand = new Brand(1L, "Example Brand");
    final Product product = new Product(1L, "Example Product");
    
    when(priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandAndProduct(date, date, brand,
        product))
        .thenReturn(Mono.empty());

    // When
    final Mono<Price> result = priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandAndProduct(
        date, date,
        brand, product);

    // Then
    StepVerifier.create(result)
        .verifyComplete();

    verify(priceRepository, times(1)).findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandAndProduct(date,
        date,
        brand, product);
  }
}
