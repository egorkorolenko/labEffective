package ru.korolenkoe.lab1effective.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.korolenkoe.lab1effective.entities.Character
import ru.korolenkoe.lab1effective.entities.Thumbnail
import java.io.IOException


class ViewModelGetHeroApi : ViewModel() {

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
