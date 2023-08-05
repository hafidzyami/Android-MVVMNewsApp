package com.example.newsapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is notifications Fragment"
//    }
//    val text: LiveData<String> = _text
    val searchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse : NewsResponse? = null


    fun getSearchNews(searchQuery : String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue((handleSearchNewsResponse(response)))
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful){
            response.body()?.let{
                searchNewsPage++
                if(searchNewsResponse == null){
                    searchNewsResponse = it
                }
                else{
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = it.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: it)
            }
        }
        return Resource.Error(response.message())
    }
}