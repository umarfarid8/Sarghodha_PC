package com.UZdevelopers.sarghodhapc.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UZdevelopers.sarghodhapc.Repositories.LoadRepository
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddWeighmentViewModel:ViewModel(){
    val loadRespositry = LoadRepository()
    val isSaved = MutableStateFlow<Boolean>(false)
    val failureMessage = MutableStateFlow<String?>(null)



    fun saveLoad(load: Load){
        viewModelScope.launch {
            val result = loadRespositry.saveLoad(load)
            if (result.isSuccess){
                isSaved.value = true
            } else {
                failureMessage.value = result.exceptionOrNull()?.message
            }
        }
    }


}