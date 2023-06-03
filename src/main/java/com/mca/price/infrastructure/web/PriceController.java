package com.mca.price.infrastructure.web;

import com.mca.price.application.Price.PriceDto;
import com.mca.price.application.Price.PriceService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController extends BaseController {

  private final PriceService priceService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad request, probably the date format is not valid, format valid example: 2020-01-01T00:00:00"),
      @ApiResponse(responseCode = "404", description = "Item not found"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public Mono<PriceDto> getPrice(
      @RequestParam("date") @DateTimeFormat(iso = ISO.DATE_TIME) final LocalDateTime date,
      @RequestParam("brandId") final Long brandId,
      @RequestParam("productId") final Long productId
  ) {

    this.logger.info("GET /prices?date={}&brandId={}&productId={}", date, brandId, productId);
    return priceService.getPrice(date, productId, brandId);
  }
}
