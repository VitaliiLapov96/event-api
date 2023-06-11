package com.event.data.generator.util;

import com.event.data.generator.model.Category;
import com.event.data.generator.model.Event;
import com.event.data.generator.model.Ticket;
import lombok.experimental.UtilityClass;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@UtilityClass
public class DataGeneratorUtil {

    private static final List<String> EVENT_HALL_NAME_LIST = List.of("Club", "Theater", "Concert hall", "Stadium");
    private static final List<String> EVENT_PLACE_LIST = List.of("Kyiv", "Kharkiv", "Odesa", "Dnipro", "Lviv");
    private static final List<Double> EVENT_PRICE_LIST = List.of(100.0, 150.0, 200.0, 500.0);

    private static final List<Category> TICKET_CATEGORY_LIST = List.of(Category.values());

    private static final int AMOUNT_MONTHS = 12;

    public List<Event> generateEventList(int amount) throws NoSuchAlgorithmException {
        List<Event> eventList = new ArrayList<>();
        Random random = SecureRandom.getInstanceStrong();
        LocalDate dateOfConcert;

        int randomIndexOfHallName;
        int randomIndexOfPlace;
        int randomIndexOfPrice;

        while (amount != 0) {
            randomIndexOfHallName = random.nextInt(EVENT_HALL_NAME_LIST.size() - 1);
            randomIndexOfPlace = random.nextInt(EVENT_PLACE_LIST.size() - 1);
            randomIndexOfPrice = random.nextInt(EVENT_PRICE_LIST.size() - 1);

            dateOfConcert = LocalDate.now()
                    .plusMonths(random.nextInt(AMOUNT_MONTHS))
                    .plusDays(random.nextInt(LocalDate.MAX.lengthOfMonth()));

            Event event = new Event()
                    .setName(EVENT_HALL_NAME_LIST.get(randomIndexOfHallName))
                    .setPlace(EVENT_PLACE_LIST.get(randomIndexOfPlace))
                    .setPrice(EVENT_PRICE_LIST.get(randomIndexOfPrice))
                    .setDate(dateOfConcert);

            eventList.add(event);
            amount--;
        }

        return eventList;
    }

    public List<Ticket> generateTicketList(int amount, Event event) throws NoSuchAlgorithmException {
        List<Ticket> ticketList = new ArrayList<>();
        Random random = SecureRandom.getInstanceStrong();

        int randomIndexOfCategory = random.nextInt(TICKET_CATEGORY_LIST.size() - 1);

        while (amount != 0) {
            Ticket ticket = new Ticket()
                    .setEvent(event)
                    .setCategory(TICKET_CATEGORY_LIST.get(randomIndexOfCategory))
                    .setCreatedDate(LocalDateTime.now());

            ticketList.add(ticket);
            amount--;
        }

        return ticketList;
    }



}
