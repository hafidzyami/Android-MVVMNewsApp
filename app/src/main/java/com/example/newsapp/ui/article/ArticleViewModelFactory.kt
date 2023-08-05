package com.example.newsapp.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.repository.NewsRepository

class ArticleViewModelFactory(
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArticleViewModel(newsRepository) as T
    }
}