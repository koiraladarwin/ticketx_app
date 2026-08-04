package com.darwin.ticketx.feature_ticket.domain.model

data class EventDetailsUi(
    val id: String,
    val title: String,
    val banner: String,

    val description: String,

    val date: String,
    val location: String,

    val category: String,

    val tags: List<String>,

    val ticketTypes: List<TicketTypeUi>
)


data class TicketTypeUi(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val available: Int
)

val eventDetailsSample = EventDetailsUi(

    id = "1",

    title = "Coldplay Live",

    banner =
        "https://picsum.photos/900/500",

    description =
        """
    Coldplay returns to Kathmandu with an 
    unforgettable live performance.

    Experience incredible visuals,
    powerful music and a night to remember.
    """.trimIndent(),


    date = "25 August 2026",

    location = "Kathmandu Stadium",

    category = "Concert",


    tags = listOf(
        "Music",
        "Live",
        "Festival",
        "International"
    ),


    ticketTypes = listOf(

        TicketTypeUi(
            "1",
            "Normal",
            "General entry access",
            1500.0,
            500
        ),

        TicketTypeUi(
            "2",
            "VIP",
            "Premium seating area",
            5000.0,
            100
        ),

        TicketTypeUi(
            "3",
            "Extra VIP",
            "Front row + backstage access",
            10000.0,
            20
        )

    )

)