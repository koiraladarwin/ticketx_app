package com.darwin.ticketx.feature_ticket.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.ticketx.feature_ticket.domain.repository.EventRepository
import com.darwin.ticketx.feature_ticket.presentation.state.EventDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {


    private val _state = MutableStateFlow(
        EventDetailsUiState()
    )

    val state: StateFlow<EventDetailsUiState> =
        _state.asStateFlow()



    fun loadEvent(
        id: String
    ) {

        viewModelScope.launch {


            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }


            repository.getEventDetails(id)
                .onSuccess { event ->

                    _state.update {
                        it.copy(
                            event = event,
                            isLoading = false
                        )
                    }

                }
                .onFailure { error ->

                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }

                }

        }
    }



    fun purchaseTicket(
        ticketTypeId: String
    ) {

        viewModelScope.launch {


            _state.update {
                it.copy(
                    isPurchasing = true,
                    purchaseSuccess = false,
                    purchaseMessage = null
                )
            }



            repository.purchaseTicket(ticketTypeId)
                .onSuccess {


                    _state.update {

                        it.copy(
                            isPurchasing = false,
                            purchaseSuccess = true,
                            purchaseMessage = "Ticket purchased successfully"
                        )

                    }

                }
                .onFailure { error ->


                    _state.update {

                        it.copy(
                            isPurchasing = false,
                            purchaseMessage = error.message
                        )

                    }

                }

        }

    }


}