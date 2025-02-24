package com.UZdevelopers.sarghodhapc.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UZdevelopers.sarghodhapc.Repositories.LoadRepository
import com.UZdevelopers.sarghodhapc.Repositories.MaterialRespository
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Material
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class QualitySearchModelClass:ViewModel() {

    val loadRespositry = LoadRepository()
    val failureMessage = MutableStateFlow<String?>(null)
    val loadData = MutableStateFlow<Load?>(null)
    val dataEmpty = MutableStateFlow<Boolean>(false)
    val material = MutableStateFlow<Material?>(null)

    fun searchEP(text: String){
        viewModelScope.launch {
            loadRespositry.getEPRecord(text).catch {
                failureMessage.value = it.message
            }
                .collect{
                    loadData.value = it.firstOrNull()
                    dataEmpty.value = it.isEmpty()
                }

        }
    }

}