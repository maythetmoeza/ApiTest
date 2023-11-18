package com.example.retrofittesttwo.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittesttwo.data.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SearchMovieViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    var allMovie = listOf<Movie>()

    var _movies = MutableStateFlow(allMovie)

    val movies = searchText
//        .debounce(1000)
        .onEach { _isSearching.update { true } }
        .combine(_movies){text, movies ->
            if(text.isBlank()){
                movies
            }else{
//                delay(1000L)
                movies.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _movies.value
        )

    fun onSearchTextChange (text: String){
        _searchText.value = text
    }
}