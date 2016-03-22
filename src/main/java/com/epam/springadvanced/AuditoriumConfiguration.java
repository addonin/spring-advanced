package com.epam.springadvanced;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.springadvanced.entity.Auditorium;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Configuration
public class AuditoriumConfiguration {

    @Bean
    public Auditorium auditorium1(
            @Value("${auditorium1.name}")
            String name,
            @Value("${auditorium1.number_of_seats}")
            Integer numberOfSeats,
            @Value("${auditorium1.vip_seats}")
            String vipSeats
    ) {
        return new Auditorium(1, name, numberOfSeats, vipSeats);
    }

    @Bean
    public Auditorium auditorium2(
            @Value("${auditorium2.name}")
            String name,
            @Value("${auditorium2.number_of_seats}")
            Integer numberOfSeats,
            @Value("${auditorium2.vip_seats}")
            String vipSeats
    ) {
        return new Auditorium(2, name, numberOfSeats, vipSeats);
    }

    @Bean
    public Auditorium auditorium3(
            @Value("${auditorium3.name}")
            String name,
            @Value("${auditorium3.number_of_seats}")
            Integer numberOfSeats,
            @Value("${auditorium3.vip_seats}")
            String vipSeats
    ) {
        return new Auditorium(3, name, numberOfSeats, vipSeats);
    }
}
