package com.darwin.ticketx.feature_ticket.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.darwin.ticketx.feature_ticket.presentation.EventDetailsUi
import com.darwin.ticketx.feature_ticket.presentation.TicketTypeUi


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketBottomSheet(
    event: EventDetailsUi,
    onDismiss: () -> Unit,
    onPurchase: (TicketTypeUi) -> Unit
) {

    var selectedTicket by remember {
        mutableStateOf<TicketTypeUi?>(null)
    }


    ModalBottomSheet(

        onDismissRequest = onDismiss,

        shape = RoundedCornerShape(
            topStart = 28.dp,
            topEnd = 28.dp
        )

    ) {


        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)

        ) {


            Text(
                text = "Choose Ticket",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )


            Spacer(
                Modifier.height(20.dp)
            )


            LazyColumn(

                verticalArrangement = Arrangement.spacedBy(12.dp),

                modifier = Modifier.heightIn(
                    max = 400.dp
                )

            ) {


                items(
                    event.ticketTypes
                ) { ticket ->


                    TicketOptionCard(

                        ticket = ticket,

                        selected =
                            selectedTicket?.id == ticket.id,


                        onClick = {

                            selectedTicket = ticket

                        }

                    )

                }

            }


            Spacer(
                Modifier.height(24.dp)
            )


            val selected = selectedTicket


            Button(

                onClick = {

                    if (selected != null) {
                        onPurchase(selected)
                    }

                },

                enabled = selected != null,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),

                shape = RoundedCornerShape(18.dp)

            ) {

                Text(

                    text =
                        if (selected == null)
                            "Select Ticket"
                        else
                            "Buy ${selected.name}"

                )

            }


            Spacer(
                Modifier.height(30.dp)
            )

        }

    }

}