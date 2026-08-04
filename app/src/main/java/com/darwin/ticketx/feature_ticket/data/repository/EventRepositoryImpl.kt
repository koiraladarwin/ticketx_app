package com.darwin.ticketx.feature_ticket.data.repository


import android.os.Build
import androidx.annotation.RequiresApi
import com.darwin.ticketx.feature_ticket.data.api.EventApi
import com.darwin.ticketx.feature_ticket.domain.model.EventUi
import com.darwin.ticketx.feature_ticket.domain.repository.EventRepository
import com.darwin.ticketx.feature_ticket.mapper.toEventDetailsUi
import com.darwin.ticketx.feature_ticket.mapper.toEventUiList
import com.darwin.ticketx.feature_ticket.presentation.EventDetailsUi
import javax.inject.Inject


class EventRepositoryImpl @Inject constructor(
    private val api: EventApi
) : EventRepository {


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getEvents(): Result<List<EventUi>> {

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
}