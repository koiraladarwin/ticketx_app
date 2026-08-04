package com.darwin.ticketx.feature_ticket.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.darwin.ticketx.feature_ticket.data.dto.DataDetailsDto
import com.darwin.ticketx.feature_ticket.data.dto.DataDto

import com.darwin.ticketx.feature_ticket.domain.model.EventModel
import com.darwin.ticketx.feature_ticket.domain.model.EventDetailsUi
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
fun DataDto.toEventUi(): EventModel {
    return EventModel(
        id = id,
        title = title,
        venue = venue,
        date = event_start_at.formatEventDate(),
        imageUrl = banner_url
    )
}


@RequiresApi(Build.VERSION_CODES.O)
fun List<DataDto>.toEventUiList(): List<EventModel> {
    return map { it.toEventUi() }
}


@RequiresApi(Build.VERSION_CODES.O)
private fun String.formatEventDate(): String {
    return try {
        val dateTime = OffsetDateTime.parse(this)

        dateTime.format(
            DateTimeFormatter.ofPattern("MMM dd, yyyy")
        )

    } catch (e: Exception) {
        this
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun DataDetailsDto.toEventDetailsUi(): EventDetailsUi {
    return EventDetailsUi(
        id = id,
        title = title,
        banner = banner_url,
        description = description,
        date = event_start_at.formatEventDate(),
        location = venue,
        category = "Event",
        tags = listOf("Music","Festival","Dance"),
        ticketTypes = ticket_types.map {
            it.toTicketTypeUi()
        }
    )
}