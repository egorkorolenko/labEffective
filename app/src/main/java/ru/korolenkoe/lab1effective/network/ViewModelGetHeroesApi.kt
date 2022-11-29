package ru.korolenkoe.lab1effective.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.korolenkoe.lab1effective.entities.Character
import ru.korolenkoe.lab1effective.repository.CharacterRepositoryApi
import java.io.IOException

class ViewModelGetHeroesApi : ViewModel() {

    private val repositoryApi = CharacterRepositoryApi()

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
                _heroes.value = repositoryApi.getCharacters().data.results
                _status.value = MarvelApiStatus.DONE
            } catch (e: IOException) {
                println(e)
                _status.value = MarvelApiStatus.ERROR
                _heroes.value = listOf()
            }
        }
    }
}