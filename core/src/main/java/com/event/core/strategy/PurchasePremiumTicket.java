package com.event.core.strategy;

import com.event.core.model.Category;
import com.event.core.model.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PurchasePremiumTicket implements PurchaseTicket {

    @Override
    public void buy(Ticket ticket) {
        // some additional logic for buying ticket
        log.info("Buy {} ticket", getTicketStrategy());

        ticket.setCategory(getTicketStrategy());
    }

    @Override
    public Category getTicketStrategy() {
        return Category.PREMIUM;
    }

}
