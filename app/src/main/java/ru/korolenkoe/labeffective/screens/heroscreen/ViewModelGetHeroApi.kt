package ru.korolenkoe.labeffective.screens.heroscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import ru.korolenkoe.labeffective.R
import ru.korolenkoe.labeffective.entities.Character
import ru.korolenkoe.labeffective.entities.Thumbnail
import ru.korolenkoe.labeffective.network.MarvelApiStatus
import ru.korolenkoe.labeffective.repository.CharacterRepositoryApi


class ViewModelGetHeroApi : ViewModel() {

    private val repositoryApi = CharacterRepositoryApi()

    private var _status = MutableStateFlow(MarvelApiStatus.LOADING)
    val status: StateFlow<MarvelApiStatus>
        get() = _status

    private var _hero = MutableStateFlow(
        Character(
            1,
            R.string.error.toString(), R.string.error.toString(), Thumbnail("", "")
        )
    )
    val hero: StateFlow<Character?> = _hero

    fun getHero(id: Int) {
        viewModelScope.launch {
            _status.value = MarvelApiStatus.LOADING
            try {
                _hero.value = repositoryApi.getCharacterById(id).data.results[0]
                _status.value = MarvelApiStatus.DONE
            } catch (e: IOException) {
                Log.e(e.message,"Error")
                _status.value = MarvelApiStatus.ERROR
            } catch (e: HttpException) {
                Log.e(e.message,"Error")
                _status.value = MarvelApiStatus.ERROR
            }
        }
    }

}
