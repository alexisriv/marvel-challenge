package org.sixelasavir.product.marvelfans.api

import org.sixelasavir.product.marvelfans.Character
import org.sixelasavir.product.marvelfans.Comic
import org.sixelasavir.product.marvelfans.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("/v1/public/characters?$OTHER_QUERIES&orderBy=name")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = API_QUERY_CHARACTERS_LIMIT
    ): Response<Character>

    @GET("/v1/public/characters/{characterId}/comics?$OTHER_QUERIES")
    suspend fun getComicsOfCharacter(@Path("characterId") characterId: Long): Response<Comic>
}
