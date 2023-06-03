package com.mca.price.application.Price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {

  private Long id;
  private Long productId;
  private Long brandId;
  private Long priceList;
  private float amount;
}
