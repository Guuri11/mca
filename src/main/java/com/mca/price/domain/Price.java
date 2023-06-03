package com.mca.price.domain;

import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Builder
@Table("price")
public class Price {

  @Id
  private Long id;
  @Column("brand_id")
  private Brand brand;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Long priceList;

  @Column("product_id")
  private Product product;
  private int priority;

  private float amount;
  @Enumerated(EnumType.STRING)
  private Currency currency;
}
