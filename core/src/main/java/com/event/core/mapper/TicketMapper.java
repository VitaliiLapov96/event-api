package com.event.core.mapper;

import com.event.core.dto.TicketDto;
import com.event.core.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    Ticket ticketDtoMapToTicket(TicketDto ticketDto);

    TicketDto ticketMapToTicketDto(Ticket ticket);

    List<Ticket> ticketDtoListMapToTicketList(List<TicketDto> ticketDtoList);

    List<TicketDto> ticketListMapToTicketDtoList(List<Ticket> ticketList);

}
