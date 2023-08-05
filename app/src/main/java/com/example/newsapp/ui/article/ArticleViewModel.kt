package com.example.newsapp.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.Article
import com.example.newsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class ArticleViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    // suspend function need viewModelScope.launch
    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

}