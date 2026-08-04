package com.darwin.ticketx.feature_ticket.mapper

import com.darwin.ticketx.feature_ticket.data.dto.TicketTypeDto
import com.darwin.ticketx.feature_ticket.presentation.TicketTypeUi

fun TicketTypeDto.toTicketTypeUi(): TicketTypeUi {
    return TicketTypeUi(
        id = id,
        name = name,
        description = description ?: "",
        price = price,
        available = remaining
    )
}