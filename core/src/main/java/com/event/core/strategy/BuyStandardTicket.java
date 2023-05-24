package com.event.core.strategy;

import com.event.core.model.Category;
import com.event.core.model.Ticket;

public class BuyStandardTicket implements BuyTicket {

    @Override
    public void buy(Ticket ticket) {
        // some additional logic for buying ticket

        ticket.setCategory(Category.STANDARD);
    }

    @Override
    public Category getTicketStrategy() {
        return Category.STANDARD;
    }

}
