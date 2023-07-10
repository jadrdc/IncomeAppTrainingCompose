package com.koombea.androidtemplate.base.state

//Default states for activities and fragments

sealed class ViewState {
    object Loading : ViewState()
    object Empty : ViewState()
    data class Error(val exception: Exception?) : ViewState()
    object Success : ViewState()
}