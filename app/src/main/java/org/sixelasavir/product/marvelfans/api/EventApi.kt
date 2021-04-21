package org.sixelasavir.product.marvelfans.api

import org.sixelasavir.product.marvelfans.Event
import org.sixelasavir.product.marvelfans.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EventApi {

    @GET("/v1/public/events?$OTHER_QUERIES&orderBy=startDate")
    suspend fun getEvents(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = API_QUERY_EVENTS_LIMIT
    ): Response<Event>
}
