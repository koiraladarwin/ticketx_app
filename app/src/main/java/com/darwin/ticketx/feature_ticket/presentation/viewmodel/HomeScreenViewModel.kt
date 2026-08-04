package com.darwin.ticketx.feature_ticket.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.ticketx.feature_ticket.domain.model.EventUi
import com.darwin.ticketx.feature_ticket.domain.repository.EventRepository
import com.darwin.ticketx.feature_ticket.presentation.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {


    private val _state = MutableStateFlow(
        HomeUiState()
    )

    val state: StateFlow<HomeUiState> =
        _state.asStateFlow()


    init {
        loadEvents()
    }


    private fun loadEvents() {

        viewModelScope.launch {

            _state.value = _state.value.copy(
                isLoading = true,
                error = null
            )


            repository.getEvents()
                .onSuccess { events ->

                    _state.value = HomeUiState(
                        events = events
                    )

                }
                .onFailure { error ->

                    _state.value = HomeUiState(
                        error = error.message
                    )

                }
        }
    }


    fun refresh() {

        viewModelScope.launch {

            _state.value = _state.value.copy(
                isRefreshing = true,
                error = null
            )


            repository.getEvents()
                .onSuccess { events ->

                    _state.value = _state.value.copy(
                        events = events,
                        isRefreshing = false
                    )

                }
                .onFailure { error ->

                    _state.value = _state.value.copy(
                        isRefreshing = false,
                        error = error.message
                    )

                }
        }
    }
}