package com.event.core.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StrategyConfig {

    @Bean
    public List<BuyTicket> buyTicketStrategy() {
        return List.of(new BuyBarTicket(), new BuyPremiumTicket(), new BuyStandardTicket());
    }

}
