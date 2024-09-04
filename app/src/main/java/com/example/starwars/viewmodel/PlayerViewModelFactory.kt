package com.example.starwars.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwars.repository.StarwarsRepository

class PlayerViewModelFactory(private val repository: StarwarsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayerViewModel(repository) as T
    }
}