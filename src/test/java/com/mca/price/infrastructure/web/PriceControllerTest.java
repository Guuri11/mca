package com.mca.price.infrastructure.web;

import com.mca.price.application.Price.PriceService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

  @Test
  void getPrice_shouldCallPlayerService() {
    // Given

    // When
    priceController.getPrice(DATE, 1L, 1L);

    // Then
    Mockito.verify(priceService)
        .getPrice(DATE, 1L, 1L);

  }

  private final LocalDateTime DATE = LocalDateTime.now();

  @Mock
  private PriceService priceService;
  @InjectMocks
  private PriceController priceController;
}