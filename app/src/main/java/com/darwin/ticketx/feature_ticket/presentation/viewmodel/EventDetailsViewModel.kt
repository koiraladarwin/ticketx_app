package com.darwin.ticketx.feature_ticket.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.ticketx.feature_ticket.domain.repository.EventRepository
import com.darwin.ticketx.feature_ticket.presentation.EventDetailsUi
import com.darwin.ticketx.feature_ticket.presentation.EventDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

            _state.value = EventDetailsUiState(
                isLoading = true
            )


            repository.getEventDetails(id)
                .onSuccess { event ->

                    _state.value = EventDetailsUiState(
                        event = event
                    )

                }
                .onFailure { error ->

                    _state.value = EventDetailsUiState(
                        error = error.message
                    )

                }

        }
    }

}