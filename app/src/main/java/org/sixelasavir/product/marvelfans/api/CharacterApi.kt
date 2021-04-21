package org.sixelasavir.product.marvelfans.api

import org.sixelasavir.product.marvelfans.Character
import org.sixelasavir.product.marvelfans.Comic
import org.sixelasavir.product.marvelfans.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET(URL_CHARACTER)
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = API_QUERY_CHARACTERS_LIMIT
    ): Response<Character>

    @GET(URL_COMICS_OF_CHARACTER)
    suspend fun getComicsOfCharacter(
        @Path("characterId") characterId: Long
    ): Response<Comic>
}
