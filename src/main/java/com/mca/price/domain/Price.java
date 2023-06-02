package com.mca.price.domain;

import java.time.LocalDate;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Builder
@Table("price")
public class Price {

  @Id
  private Long id;
  private Brand brand;
  private LocalDate startDate;
  private LocalDate endDate;
  private PriceList priceList;
  private Product product;
  private int priority;

  private float amount;
  @Enumerated(EnumType.STRING)
  private Currency currency;
}
