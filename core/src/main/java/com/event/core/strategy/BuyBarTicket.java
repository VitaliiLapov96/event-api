package com.event.core.strategy;

import com.event.core.dto.TicketDto;
import com.event.core.model.Category;
import com.event.core.model.Ticket;

public class BuyBarTicket implements BuyTicket {

    @Override
    public void buy(Ticket ticket) {
        // some additional logic for buying ticket

        ticket.setCategory(Category.BAR);
    }

    @Override
    public Category getTicketStrategy() {
        return Category.BAR;
    }

}
