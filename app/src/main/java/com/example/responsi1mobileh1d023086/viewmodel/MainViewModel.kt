package com.example.responsi1mobileh1d023065.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023065.data.model.HeadCoach
import com.example.responsi1mobileh1d023065.data.model.TeamSquad
import com.example.responsi1mobileh1d023086.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _coach = MutableLiveData<HeadCoach?>()
    val coach: LiveData<HeadCoach?> = _coach

    private val _squad = MutableLiveData<List<TeamSquad>>()
    val squad: LiveData<List<TeamSquad>> = _squad

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchCoach(clubId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = RetrofitInstance.api.getHeadCoach(clubId)
                if (response.isSuccessful) {
                    response.body()?.let { coachResponse ->
                        _coach.value = coachResponse.coach
                        _error.value = "Coach data loaded successfully"
                        Log.d("MainViewModel", "Coach: ${coachResponse.coach}")
                    } ?: run {
                        _error.value = "Empty response body"
                    }
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                    Log.e("MainViewModel", "Error response: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _error.value = "Exception: ${e.message}"
                Log.e("MainViewModel", "Exception fetching coach", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchSquad(clubId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = RetrofitInstance.api.getClubSquad(clubId)
                if (response.isSuccessful) {
                    response.body()?.let { clubResponse ->
                        _squad.value = clubResponse.squad
                        _error.value = "Squad fetched: ${clubResponse.squad.size} players"
                        Log.d("MainViewModel", "Squad: ${clubResponse.squad}")
                    } ?: run {
                        _error.value = "Empty response body"
                    }
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                    Log.e("MainViewModel", "Error response: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _error.value = "Exception: ${e.message}"
                Log.e("MainViewModel", "Exception fetching squad", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}