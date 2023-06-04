package com.mca.price.application.Price;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.mca.price.domain.Brand;
import com.mca.price.domain.Price;
import com.mca.price.domain.Product;
import com.mca.price.domain.mappers.PriceMapper;
import com.mca.price.infrastructure.persistence.BrandRepository;
import com.mca.price.infrastructure.persistence.PriceRepository;
import com.mca.price.infrastructure.persistence.ProductRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

  @Test
  void GivenADateAndProductIdAndBrandId_WhenGetPrice_thenReturnPriceDto() {
    // Given
    when(
        priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
            DATE, DATE, 1L, 1L)).thenReturn(
        Mono.just(price));
    when(priceMapper.toDto(price)).thenReturn(priceDto);
    when(brandRepository.findById(ID)).thenReturn(Mono.just(brand));
    when(brand.getId()).thenReturn(ID);
    when(productRepository.findById(ID)).thenReturn(Mono.just(product));
    when(product.getId()).thenReturn(ID);

    // When
    final PriceDto result = priceService.getPrice(DATE, 1L, 1L)
        .block();
    // Then
    assertNotNull(result);
  }

  @Test
  void GivenADateAndProductIdAndBrandId_WhenGetPrice_thenReturnNotFoundException() {
    // Given
    when(
        priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
            DATE, DATE, ID, ID)).thenReturn(
        Mono.empty());
    when(brandRepository.findById(ID)).thenReturn(Mono.just(brand));
    when(brand.getId()).thenReturn(ID);
    when(productRepository.findById(ID)).thenReturn(Mono.just(product));
    when(product.getId()).thenReturn(ID);

    try {
      // When
      priceService.getPrice(DATE, 1L, 1L)
          .block();
    } catch (final NotFoundException e) {
      assertThat(e.getMessage()).isEqualTo("Could not find item");
    }
  }

  private final LocalDateTime DATE = LocalDateTime.now();
  private final Long ID = 1L;

  @Mock
  private PriceMapper priceMapper;
  @Mock
  private Price price;
  @Mock
  private PriceDto priceDto;
  @Mock
  private PriceRepository priceRepository;
  @Mock
  private BrandRepository brandRepository;
  @Mock
  private ProductRepository productRepository;
  @Mock
  private Product product;
  @Mock
  private Brand brand;
  @InjectMocks
  private PriceService priceService;
}