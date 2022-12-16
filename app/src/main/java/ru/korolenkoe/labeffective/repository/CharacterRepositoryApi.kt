package ru.korolenkoe.labeffective.repository

import ru.korolenkoe.labeffective.entities.Response
import ru.korolenkoe.labeffective.network.getService

class CharacterRepositoryApi {

    suspend fun getCharacterById(id: Int): Response {
//        return MarvelApi.getService().getHero(id)
        return getService().getHero(id)
    }

    suspend fun getCharacters(): Response {
//        return MarvelApi.getService().getListHeroes()
        return getService().getListHeroes()
    }
}
