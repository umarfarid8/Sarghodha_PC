package com.UZdevelopers.ui.Auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.UZdevelopers.Repositories.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel(){
    val authRepository= AuthRepository()

    val currentUser= MutableStateFlow<FirebaseUser?>(null)
    val failureMessage= MutableStateFlow<String?>(null)
    val resetResponse= MutableStateFlow<Boolean?>(null)

    fun login(email:String,password:String){
        viewModelScope.launch {
            val result=authRepository.login(email,password)
            if (result.isSuccess){
                currentUser.value=result.getOrThrow()
            }else{
                failureMessage.value=result.exceptionOrNull()?.message
            }
        }
    }
    fun resetPassword(email:String){
        viewModelScope.launch {
            val result=authRepository.resetPassword(email)
            if (result.isSuccess){
                resetResponse.value=result.getOrThrow()
            }else{
                failureMessage.value=result.exceptionOrNull()?.message
            }
        }
    }
    fun signUp(email:String,password:String,name:String){
        viewModelScope.launch {
            val result=authRepository.signup(email,password,name)
            if (result.isSuccess){
                currentUser.value=result.getOrThrow()
            }else{
                failureMessage.value=result.exceptionOrNull()?.message
            }
        }
    }

    fun checkUser(){
        currentUser.value=authRepository.getCurrentUser()
    }
    fun logout(){
        viewModelScope.launch {
            val result=authRepository.logout()
            if (result.isSuccess){
                currentUser.value=null
            }else{
                failureMessage.value=result.exceptionOrNull()?.message
            }
        }
    }
}