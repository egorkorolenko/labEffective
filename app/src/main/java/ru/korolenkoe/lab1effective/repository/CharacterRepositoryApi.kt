package ru.korolenkoe.lab1effective.repository

import ru.korolenkoe.lab1effective.entities.Response
import ru.korolenkoe.lab1effective.network.MarvelApi

class CharacterRepositoryApi {

    suspend fun getCharacterById(id: Int): Response {
        return MarvelApi.getService().getHero(id)
    }

    suspend fun getCharacters(): Response {
        return MarvelApi.getService().getListHeroes()
    }
}