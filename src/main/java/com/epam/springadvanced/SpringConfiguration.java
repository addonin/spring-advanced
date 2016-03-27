package com.epam.springadvanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({AspectConfiguration.class,
        DataConfiguration.class,
        AuditoriumConfiguration.class,
        SecurityConfiguration.class})
@PropertySource("classpath:data/auditorium1.properties")
@PropertySource("classpath:data/auditorium2.properties")
@PropertySource("classpath:data/auditorium3.properties")
@ComponentScan("com.epam.springadvanced.service")
public class SpringConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
