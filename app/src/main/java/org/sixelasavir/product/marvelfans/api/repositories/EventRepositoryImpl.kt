package org.sixelasavir.product.marvelfans.api.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.sixelasavir.product.marvelfans.Event
import org.sixelasavir.product.marvelfans.api.EventApi

class EventRepositoryImpl(private val api:EventApi): EventRepository {

    override fun getEvents(offset: Int): Flow<List<Event>> = flow{
        val result = api.getEvents(offset).data.results
        emit(result)
    }.flowOn(Dispatchers.IO)
}
