package com.yakogdan.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yakogdan.data.repository.NewsFeedRepositoryImpl
import com.yakogdan.domain.usecase.CheckAuthStateDataUseCase
import com.yakogdan.domain.usecase.GetAuthStateFlowUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImpl(application)

    private val getAuthStateFlowUseCase = GetAuthStateFlowUseCase(repository)
    private val checkAuthStateDataUseCase = CheckAuthStateDataUseCase(repository)

    val authState = getAuthStateFlowUseCase()

    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateDataUseCase
        }
    }
}
