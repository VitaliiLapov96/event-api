package com.event.core.strategy;

import com.event.core.model.Category;
import com.event.core.model.Ticket;

public interface PurchaseTicket {

    void buy(Ticket ticket);

    Category getTicketStrategy();

}
