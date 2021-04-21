package org.sixelasavir.product.marvelfans.api.repositories

import kotlinx.coroutines.flow.Flow
import org.sixelasavir.product.marvelfans.Event

interface EventRepository {

    fun getEvents(offset: Int): Flow<List<Event>>
}
