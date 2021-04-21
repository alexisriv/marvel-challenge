package org.sixelasavir.product.marvelfans.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.sixelasavir.product.marvelfans.Character
import org.sixelasavir.product.marvelfans.Comic
import org.sixelasavir.product.marvelfans.api.CharacterApi

class CharacterRepositoryImpl(private val api: CharacterApi) : CharacterRepository {

    override fun getCharacters(offset: Int): Flow<List<Character>> = flow {
        val results = api.getCharacters(offset).data.results
        emit(results)
    }.flowOn(IO)

    override fun getComicsOfCharacter(characterId: Long): Flow<List<Comic>> = flow {
        val result = api.getComicsOfCharacter(characterId).data.results
        emit(result)
    }.flowOn(IO)
}
