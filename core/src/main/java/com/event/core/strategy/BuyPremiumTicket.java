package com.event.core.strategy;

import com.event.core.model.Category;
import com.event.core.model.Ticket;

public class BuyPremiumTicket implements BuyTicket {

    @Override
    public void buy(Ticket ticket) {
        // some additional logic for buying ticket

        ticket.setCategory(Category.PREMIUM);
    }

    @Override
    public Category getTicketStrategy() {
        return Category.PREMIUM;
    }

}
