package ru.korolenkoe.lab1effective

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.korolenkoe.lab1effective.models.Character
import ru.korolenkoe.lab1effective.models.Thumbnail
import ru.korolenkoe.lab1effective.network.MarvelApi


enum class MarvelApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _heroes = MutableStateFlow<List<Character>>(emptyList())
    val heroes: StateFlow<List<Character>> = _heroes

    private val _status = MutableStateFlow(MarvelApiStatus.LOADING)
    val status: StateFlow<MarvelApiStatus>
        get() = _status

    init {
        getHeroes()
    }

   private fun getHeroes() {
        viewModelScope.launch {
            try {
                _status.value = MarvelApiStatus.LOADING
                _heroes.value = MarvelApi.retrofitService
                    .getListHeroes("https://gateway.marvel.com/v1/public/characters?ts=1668014292&apikey=f222f067928c0d48f7c8bcb401fa04a7&hash=b6900c48887e2ef1543293bb64d045c5")
                    .data
                    .results
                _status.value = MarvelApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarvelApiStatus.ERROR
                _heroes.value = listOf()
            }
        }
    }
}

class OverviewViewModel2() : ViewModel() {

    private val _status = MutableStateFlow(MarvelApiStatus.LOADING)
    val status: StateFlow<MarvelApiStatus>
        get() = _status

    private val _hero = MutableStateFlow(Character(1,"Error","Error", Thumbnail("","")))
    val hero: StateFlow<Character?> = _hero

    fun getHero(id:Int){
        viewModelScope.launch {
            try {
                _hero.value = MarvelApi.retrofitService
                    .getHero("https://gateway.marvel.com/v1/public/characters/${id}" +
                            "?ts=1668014292&apikey=f222f067928c0d48f7c8bcb401fa04a7&" +
                            "hash=b6900c48887e2ef1543293bb64d045c5").data.results[0]
                _status.value = MarvelApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarvelApiStatus.ERROR
            }
        }
    }
}
