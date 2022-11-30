package ru.korolenkoe.labeffective.screens.mainscreen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.korolenkoe.labeffective.entities.Character
import ru.korolenkoe.labeffective.network.MarvelApiStatus
import ru.korolenkoe.labeffective.repository.CharacterRepositoryApi
import java.io.IOException

class ViewModelGetHeroesApi : ViewModel() {

    private val repositoryApi = CharacterRepositoryApi()

    private val _heroes = MutableStateFlow<List<Character>>(emptyList())
    val heroes: StateFlow<List<Character>> = _heroes

    private val _status = MutableStateFlow(MarvelApiStatus.LOADING)
    val status: StateFlow<MarvelApiStatus> = _status

    init {
        getHeroes()
    }

    private fun getHeroes() {
        viewModelScope.launch {
            _status.value = MarvelApiStatus.LOADING
            try {
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
