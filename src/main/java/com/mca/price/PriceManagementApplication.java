package com.mca.price;

import io.r2dbc.spi.ConnectionFactory;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@OpenAPIDefinition(info = @Info(title = "Price Management Backend service", version = "1.0", description = "Documentation Price Management APIs v1.0"))
@EnableJpaRepositories(basePackages = {"com.mca.price.infrastructure.persistence"})
@SpringBootApplication
public class PriceManagementApplication {

  public static void main(final String[] args) {

    SpringApplication.run(PriceManagementApplication.class, args);
  }

  @Bean
  ConnectionFactoryInitializer initializer(final ConnectionFactory connectionFactory) {

    final ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
    initializer.setConnectionFactory(connectionFactory);
    initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

    return initializer;
  }

  @Bean
  public ModelMapper modelMapper() {

    return new ModelMapper();
  }
}
