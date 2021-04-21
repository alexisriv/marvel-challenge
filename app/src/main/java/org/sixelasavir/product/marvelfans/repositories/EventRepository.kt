package org.sixelasavir.product.marvelfans.repositories

import kotlinx.coroutines.flow.Flow
import org.sixelasavir.product.marvelfans.Comic
import org.sixelasavir.product.marvelfans.Event

interface EventRepository {

    fun getEvents(offset: Int): Flow<List<Event>>

    fun getComicsOfEvent(eventId: Long): Flow<List<Comic>>
}
