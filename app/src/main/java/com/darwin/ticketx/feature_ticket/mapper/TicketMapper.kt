package com.darwin.ticketx.feature_ticket.mapper

import com.darwin.ticketx.feature_ticket.data.dto.Data
import com.darwin.ticketx.feature_ticket.data.dto.TicketTypeDto
import com.darwin.ticketx.feature_ticket.domain.model.TicketModel
import com.darwin.ticketx.feature_ticket.domain.model.TicketTypeUi

fun TicketTypeDto.toTicketTypeUi(): TicketTypeUi {
    return TicketTypeUi(
        id = id,
        name = name,
        description = description ?: "",
        price = price,
        available = remaining
    )
}
fun Data.toTicketUiState(): TicketModel {
    return TicketModel(
        id = id,
        image = event.banner_url,
        eventTitle = event.title,
        ticketType = ticket_type.name,
        venue = event.venue,
        eventDate = event.start_at,
        price = "$${ticket_type.price}"
    )
}


fun List<Data>.toTicketUiStateList(): List<TicketModel> {
    return map {
        it.toTicketUiState()
    }
}