package com.mca.price.application.Price;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class PriceServiceIT {

  private final PriceService underTest;

  @Autowired
  public PriceServiceIT(final PriceService underTest) {

    this.underTest = underTest;
  }

  @Test
  void requiredTestOne() {

    final PriceDto result = underTest.getPrice(LocalDateTime.of(2020, 6, 14, 10, 0), 35455L, 1L)
        .block();
    assertThat(result.getId()).isEqualTo(1L);
  }

  @Test
  void requiredTestTwo() {

    final PriceDto result = underTest.getPrice(LocalDateTime.of(2020, 6, 14, 16, 0), 35455L, 1L)
        .block();
    assertThat(result.getId()).isEqualTo(2L);
  }

  @Test
  void requiredTestThree() {

    final PriceDto result = underTest.getPrice(LocalDateTime.of(2020, 6, 14, 21, 0), 35455L, 1L)
        .block();
    assertThat(result.getId()).isEqualTo(1L);
  }

  @Test
  void requiredTestFour() {

    final PriceDto result = underTest.getPrice(LocalDateTime.of(2020, 6, 15, 10, 0), 35455L, 1L)
        .block();
    assertThat(result.getId()).isEqualTo(3L);
  }

  @Test
  void requiredTestFive() {

    final PriceDto result = underTest.getPrice(LocalDateTime.of(2020, 6, 16, 16, 0), 35455L, 1L)
        .block();
    assertThat(result.getId()).isEqualTo(4L);
  }
}