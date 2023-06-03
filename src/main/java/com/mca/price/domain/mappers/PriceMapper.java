package com.mca.price.domain.mappers;

import com.mca.price.application.Price.PriceDto;
import com.mca.price.domain.Price;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

  @Autowired
  private ModelMapper modelMapper;

  public PriceDto toDto(final Price entity) {

    if (Objects.isNull(entity)) {
      return null;
    }
    final PriceDto priceDto = modelMapper.map(entity, PriceDto.class);
    priceDto.setBrandId(entity.getBrand()
        .getId());
    priceDto.setProductId(entity.getProduct()
        .getId());
    return priceDto;
  }

  public Price toEntity(final PriceDto dto) {

    return Objects.isNull(dto) ? null : modelMapper.map(dto, Price.class);
  }
}