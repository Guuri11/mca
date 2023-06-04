package com.mca.price.application.Price;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.mca.price.domain.Price;
import com.mca.price.domain.mappers.PriceMapper;
import com.mca.price.infrastructure.persistence.PriceRepository;
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
            DATE, DATE, 1L, 1L)).thenReturn(
        Mono.empty());

    try {
      // When
      priceService.getPrice(DATE, 1L, 1L)
          .block();
    } catch (final NotFoundException e) {
      assertThat(e.getMessage()).isEqualTo("Could not find item");
    }
  }

  private final LocalDateTime DATE = LocalDateTime.now();

  @Mock
  private PriceMapper priceMapper;
  @Mock
  private Price price;
  @Mock
  private PriceDto priceDto;
  @Mock
  private PriceRepository priceRepository;
  @InjectMocks
  private PriceService priceService;
}