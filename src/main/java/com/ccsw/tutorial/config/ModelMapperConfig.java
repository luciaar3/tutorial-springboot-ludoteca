package com.ccsw.tutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ccsw
 *
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public org.modelmapper.ModelMapper getModelMapper() {

        return new org.modelmapper.ModelMapper();
    }

}
