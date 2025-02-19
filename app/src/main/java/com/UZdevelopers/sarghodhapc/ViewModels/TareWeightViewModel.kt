package com.UZdevelopers.sarghodhapc.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UZdevelopers.sarghodhapc.Repositories.LoadRepository
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Material
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TareWeightViewModel: ViewModel() {
    val loadRespositry = LoadRepository()
    val failureMessage = MutableStateFlow<String?>(null)
    val material = MutableStateFlow<Material?>(null)
    val isUpdated = MutableStateFlow<Boolean>(false)

    fun updateLoad(load: Load){
        viewModelScope.launch {
            val result = loadRespositry.updateRequest(load)
            if (result.isSuccess){
                isUpdated.value = true
            } else {
                failureMessage.value = result.exceptionOrNull()?.message
            }
        }
    }
}