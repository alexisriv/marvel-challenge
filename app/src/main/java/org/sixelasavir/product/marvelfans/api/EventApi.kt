package org.sixelasavir.product.marvelfans.api

import org.sixelasavir.product.marvelfans.Comic
import org.sixelasavir.product.marvelfans.Event
import org.sixelasavir.product.marvelfans.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EventApi {

    @GET(URL_EVENTS)
    suspend fun getEvents(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = API_QUERY_EVENTS_LIMIT
    ): Response<Event>

    @GET(URL_COMICS_OF_EVENT)
    suspend fun getComicsOfEvent(
        @Path("eventId") eventId: Long
    ): Response<Comic>
}
