package com.UZdevelopers.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UZdevelopers.Repositories.AuthRepository
import com.UZdevelopers.UI.ModelClass.Users
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class  MainViewModel : ViewModel() {
    val authRepository = AuthRepository()

    val currentUser = MutableStateFlow<Users?>(null)

    init {
        currentUser.value=authRepository.getCurrentUser()
    }

    fun logout(){
        viewModelScope.launch {
            authRepository.logout()
            currentUser.value=null
        }
    }


}