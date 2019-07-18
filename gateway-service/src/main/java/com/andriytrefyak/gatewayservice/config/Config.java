package com.andriytrefyak.gatewayservice.config;

import com.andriytrefyak.gatewayservice.filter.PreLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public PreLogFilter preFilter() {
        return new PreLogFilter();
    }
}
