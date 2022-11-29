package ru.korolenkoe.lab1effective.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.korolenkoe.lab1effective.models.Character
import ru.korolenkoe.lab1effective.models.Thumbnail
import java.io.IOException


enum class MarvelApiStatus { LOADING, ERROR, DONE }

class ViewModelHeroes : ViewModel() {

    private val _heroes = MutableLiveData<List<Character>>()
    val heroes: LiveData<List<Character>> = _heroes

    private val _status = MutableLiveData<MarvelApiStatus>()
    val status: LiveData<MarvelApiStatus> = _status


init {
        getHeroes()
    }

    private fun getHeroes() {
        viewModelScope.launch {
            try {
                _status.value = MarvelApiStatus.LOADING
                _heroes.value = MarvelApi.getService()
                    .getListHeroes()
                    .data
                    .results
                _status.value = MarvelApiStatus.DONE
            } catch (e: IOException) {
                println(e)
                _status.value = MarvelApiStatus.ERROR
                _heroes.value = listOf()
            }
        }
    }
}

class ViewModelGetHero : ViewModel() {

    private var _status = MutableStateFlow(MarvelApiStatus.LOADING)
    val status: StateFlow<MarvelApiStatus>
        get() = _status

    private var _hero = MutableStateFlow(Character(1, "Error", "Error", Thumbnail("", "")))
    val hero: StateFlow<Character?> = _hero

    fun getHero(id: Int) {
        viewModelScope.launch {
            _status.value = MarvelApiStatus.LOADING
            try {
                _hero.value = MarvelApi.getService()
                    .getHero(id).data.results[0]
                _status.value = MarvelApiStatus.DONE
            } catch (e: IOException) {
                println(e)
                _status.value = MarvelApiStatus.ERROR
            }
        }
    }

}
