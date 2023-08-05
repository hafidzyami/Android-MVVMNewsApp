package com.example.newsapp.ui.savedNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.ui.home.HomeViewModel

class SavedNewsViewModelFactory(
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SavedNewsViewModel(newsRepository) as T
    }
}