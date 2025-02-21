package com.UZdevelopers.sarghodhapc.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UZdevelopers.sarghodhapc.Repositories.LoadRepository
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HistoryWeightmentViewModel:ViewModel() {

    val loadRepository = LoadRepository()
    val failureMessage = MutableStateFlow<String?>(null)
    val data = MutableStateFlow<List<Load>?>(null)


    fun readData() {
        viewModelScope.launch {
            loadRepository.getLoads().catch {
                failureMessage.value = it.message
            }
                .collect {
                    data.value = it
                }
        }
    }

}