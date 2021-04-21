package org.sixelasavir.product.marvelfans.repositories

import kotlinx.coroutines.flow.Flow
import org.sixelasavir.product.marvelfans.Character
import org.sixelasavir.product.marvelfans.Comic

interface CharacterRepository {

    fun getCharacters(offset: Int): Flow<List<Character>>

    fun getComicsOfCharacter(characterId: Long): Flow<List<Comic>>
}
