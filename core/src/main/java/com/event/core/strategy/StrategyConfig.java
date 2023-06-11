package com.event.core.strategy;

import com.event.core.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class StrategyConfig {

    private final Set<PurchaseTicket> purchaseTicketTypeSet;

    @Bean
    public Map<Category, PurchaseTicket> purchaseTicketMap() {
        return purchaseTicketTypeSet.stream()
                .collect(Collectors.toMap(PurchaseTicket::getTicketStrategy, purchaseTicket -> purchaseTicket));
    }

}
