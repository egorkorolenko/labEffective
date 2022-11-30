package ru.korolenkoe.labeffective.screens.mainscreen.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.korolenkoe.labeffective.entities.Character
import ru.korolenkoe.labeffective.network.MarvelApiStatus
import ru.korolenkoe.labeffective.repository.CharacterRepositoryApi
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
