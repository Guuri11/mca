package com.mca.price.application.Price;

public class NotFoundException extends RuntimeException {

  public NotFoundException() {

    super("Could not find item");
  }

  public NotFoundException(final Long id) {

    super("Could not find item " + id);
  }
}