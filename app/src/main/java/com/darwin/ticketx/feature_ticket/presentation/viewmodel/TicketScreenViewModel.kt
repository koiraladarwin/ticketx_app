package com.darwin.ticketx.feature_ticket.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.ticketx.feature_ticket.domain.model.TicketModel
import com.darwin.ticketx.feature_ticket.domain.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class TicketScreenState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val tickets: List<TicketModel> = emptyList(),
    val error: String? = null
)


@HiltViewModel
class TicketViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {


    private val _state = MutableStateFlow(
        TicketScreenState()
    )

    val state: StateFlow<TicketScreenState> =
        _state.asStateFlow()


    init {
        getTickets()
    }


    private fun getTickets(
        isRefresh: Boolean = false
    ) {

        viewModelScope.launch {


            if (isRefresh) {

                _state.value = _state.value.copy(
                    isRefreshing = true,
                    error = null
                )

            } else {

                _state.value = _state.value.copy(
                    isLoading = true,
                    error = null
                )
            }


            repository.getMyTickets()
                .onSuccess { tickets ->


                    _state.value = _state.value.copy(

                        isLoading = false,

                        isRefreshing = false,

                        tickets = tickets,

                        error = null
                    )

                }
                .onFailure { error ->


                    _state.value = _state.value.copy(

                        isLoading = false,

                        isRefreshing = false,

                        error = error.message
                    )

                }

        }
    }


    fun refresh() {
        getTickets(
            isRefresh = true
        )
    }
}