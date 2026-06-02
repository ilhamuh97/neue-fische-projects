package org.example.pokeapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration(proxyBeanMethods = false)
public class RestClientConfig {

    // https://docs.spring.io/spring-boot/reference/io/rest-client.html
    @Bean
    public RestClient restClientPokemon(RestClient.Builder restClientBuilder) {
        return restClientBuilder
                .baseUrl("https://pokeapi.co/api/v2")
                .build();
    }
}
