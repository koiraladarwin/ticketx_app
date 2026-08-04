package com.darwin.ticketx.feature_auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darwin.ticketx.feature_auth.data.dto.SignupRequest
import com.darwin.ticketx.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {


    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()



    fun signup(
        email: String,
        username: String,
        password: String
    ) {

        viewModelScope.launch {

            _state.value = SignupState(
                isLoading = true
            )


            val result = repository.signup(
                SignupRequest(
                    email = email,
                    username = username,
                    password = password
                )
            )


            result.onSuccess {
                    Log.d("Testing","Sucess")
                    _state.value = SignupState(
                        success = true
                    )

                }
                .onFailure {
                    Log.d("Testing",it.message.toString())
                    _state.value = SignupState(
                        error = it.message
                            ?: "Signup failed"
                    )

                }

        }

    }


    fun clearState(){

        _state.value = SignupState()

    }

}