package com.darwin.ticketx.feature_ticket.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.darwin.ticketx.feature_ticket.presentation.TicketTypeUi


@Composable
fun TicketOptionCard(
    ticket: TicketTypeUi,
    selected: Boolean,
    onClick: () -> Unit
){

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .background(

                if(selected)
                    MaterialTheme.colorScheme.primary.copy(.12f)
                else
                    MaterialTheme.colorScheme.surfaceVariant,

                RoundedCornerShape(20.dp)

            )
            .clickable(onClick = onClick)
            .padding(16.dp),

        verticalAlignment = Alignment.CenterVertically

    ){


        Column(
            modifier = Modifier.weight(1f)
        ){


            Text(

                text = ticket.name,

                style = MaterialTheme.typography.titleMedium,

                fontWeight = FontWeight.Bold

            )


            Spacer(
                Modifier.height(4.dp)
            )


            Text(

                text = ticket.description,

                style = MaterialTheme.typography.bodySmall,

                color = MaterialTheme.colorScheme.onSurfaceVariant

            )


        }



        Column(

            horizontalAlignment = Alignment.End

        ){

            Text(

                text = "Rs ${ticket.price}",

                fontWeight = FontWeight.Bold

            )


            Text(

                text = "${ticket.available} left",

                style = MaterialTheme.typography.labelSmall

            )

        }


    }

}



@Composable
fun QuantitySelector(
    quantity:Int,
    onIncrease:()->Unit,
    onDecrease:()->Unit
){

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(18.dp)
            )
            .padding(12.dp),

        horizontalArrangement = Arrangement.Center,

        verticalAlignment = Alignment.CenterVertically

    ){


        IconButton(
            onClick = onDecrease
        ){

            Icon(
                Icons.Default.Remove,
                null
            )

        }



        Text(

            text = quantity.toString(),

            modifier = Modifier.padding(horizontal = 24.dp),

            style = MaterialTheme.typography.titleLarge,

            fontWeight = FontWeight.Bold

        )



        IconButton(
            onClick = onIncrease
        ){

            Icon(
                Icons.Default.Add,
                null
            )

        }


    }

}