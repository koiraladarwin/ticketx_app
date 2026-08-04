package com.darwin.ticketx.feature_ticket.data.repository


import android.os.Build
import androidx.annotation.RequiresApi
import com.darwin.ticketx.feature_ticket.data.api.EventApi
import com.darwin.ticketx.feature_ticket.data.dto.PurchaseRequest
import com.darwin.ticketx.feature_ticket.domain.model.EventModel
import com.darwin.ticketx.feature_ticket.domain.repository.EventRepository
import com.darwin.ticketx.feature_ticket.mapper.toEventDetailsUi
import com.darwin.ticketx.feature_ticket.mapper.toEventUiList
import com.darwin.ticketx.feature_ticket.domain.model.EventDetailsUi
import com.darwin.ticketx.feature_ticket.domain.model.TicketModel
import com.darwin.ticketx.feature_ticket.mapper.toTicketUiStateList
import javax.inject.Inject


class EventRepositoryImpl @Inject constructor(
    private val api: EventApi
) : EventRepository {


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getEvents(): Result<List<EventModel>> {

        return try {

            val response = api.getEvents()


            if (response.success) {

                Result.success(
                    response.data.toEventUiList()
                )

            } else {

                Result.failure(
                    Exception(response.message)
                )

            }

        } catch (e: Exception) {

            Result.failure(e)

        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getEventDetails(
        id: String
    ): Result<EventDetailsUi> {

        return try {

            val response = api.getEventDetails(id)


            if (response.success) {

                Result.success(
                    response.data.toEventDetailsUi()
                )

            } else {

                Result.failure(
                    Exception(response.message)
                )

            }


        } catch (e: Exception) {

            Result.failure(e)

        }
    }

    override suspend fun purchaseTicket(
        ticketTypeId: String
    ): Result<Boolean> {

        return try {

            val response = api.purchaseTicket(
                PurchaseRequest(
                    ticket_type_id = ticketTypeId
                )
            )


            when {
                response.isSuccessful -> {
                    Result.success(true)
                }


                response.code() == 404 -> {
                    Result.failure(
                        Exception(
                            "Ticket not found"
                        )
                    )
                }


                response.code() == 409 -> {
                    Result.failure(
                        Exception(
                            "Ticket already purchased"
                        )
                    )
                }


                else -> {
                    Result.failure(
                        Exception(
                            "Purchase failed"
                        )
                    )
                }

            }


        } catch (e: Exception) {

            Result.failure(e)

        }

    }

    override suspend fun getMyTickets(): Result<List<TicketModel>> {

        return try {

            val response = api.getMyTickets()

            if (response.success) {

                Result.success(
                    response.data.toTicketUiStateList()
                )

            } else {

                Result.failure(
                    Exception(response.message)
                )

            }

        } catch (e: Exception) {

            Result.failure(e)

        }
    }

}