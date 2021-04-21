package org.sixelasavir.product.marvelfans.repositories

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.sixelasavir.product.marvelfans.Comic
import org.sixelasavir.product.marvelfans.Event
import org.sixelasavir.product.marvelfans.api.EventApi

class EventRepositoryImpl(private val api: EventApi) : EventRepository {

    override fun getEvents(offset: Int): Flow<List<Event>> = flow {
        val result = api.getEvents(offset).data.results
        emit(result)
    }.flowOn(IO)

    override fun getComicsOfEvent(eventId: Long): Flow<List<Comic>> = flow {
        val result = api.getComicsOfEvent(eventId).data.results
        emit(result)
    }.flowOn(IO)
}
