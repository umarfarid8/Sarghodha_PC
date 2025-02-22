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

class LimitViewModel:ViewModel() {
    val materialRespositry = MaterialRespository()
    val isSaved = MutableStateFlow<Boolean>(false)
    val failureMessage = MutableStateFlow<String?>(null)
    val material = MutableStateFlow<Material?>(null)
    val isEmtpy = MutableStateFlow<Boolean>(false)



    fun saveMaterial(material: Material){
        viewModelScope.launch {
            val result = materialRespositry.saveMaterial(material)
            if (result.isSuccess){
                isSaved.value = true
            } else {
                failureMessage.value = result.exceptionOrNull()?.message
            }
        }
    }

    fun updateMaterial(material: Material){
        viewModelScope.launch {
            val result = materialRespositry.updateRequest(material)
            if (result.isSuccess){
                isSaved.value = true
            } else {
                failureMessage.value = result.exceptionOrNull()?.message
            }
        }
    }
    fun getMaterial(mtname: String){
        viewModelScope.launch {
            materialRespositry.getMaterial(mtname).catch {
                failureMessage.value = it.message
            }
                .collect{
                    material.value = it.firstOrNull()
                    isEmtpy.value = it.isEmpty()
                }

        }
    }

}
