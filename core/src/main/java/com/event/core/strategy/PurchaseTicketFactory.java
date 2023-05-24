package com.event.core.strategy;

import com.event.core.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class PurchaseTicketFactory {

    private Map<Category, PurchaseTicket> purchaseTicketMap;

    @Autowired
    public PurchaseTicketFactory(Set<PurchaseTicket> purchaseTicketTypeSet) {
        createStrategy(purchaseTicketTypeSet);
    }

    public PurchaseTicket getPurchaseTicketStrategy(Category category) {
        return purchaseTicketMap.get(category);
    }

    private void createStrategy(Set<PurchaseTicket> purchaseTicketTypeSet) {
        purchaseTicketMap = new HashMap<>();
        purchaseTicketTypeSet.forEach(purchaseTicketType -> purchaseTicketMap.put(purchaseTicketType.getTicketStrategy(), purchaseTicketType));
    }

}
