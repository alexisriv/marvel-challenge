package org.sixelasavir.product.marvelfans.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.sixelasavir.product.marvelfans.Comic
import org.sixelasavir.product.marvelfans.Event
import org.sixelasavir.product.marvelfans.EventWrapper
import org.sixelasavir.product.marvelfans.repositories.EventRepository

class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {

    private val _events: MutableLiveData<List<EventWrapper>> = MutableLiveData()
    val events: LiveData<List<EventWrapper>>
        get() = _events

    private val _eventWrapper: MutableLiveData<EventWrapper> = MutableLiveData()
    val eventWrapper: LiveData<EventWrapper>
        get() = _eventWrapper

    init {
        loadEvents(0)
    }

    fun loadEvents(offset: Int): Job = viewModelScope.launch {
        eventRepository.getEvents(offset).collect {
            _events.value = it.map { e ->
                EventWrapper(e)
            }
        }
    }

    fun loadComics(eventW: EventWrapper) = viewModelScope.launch {
        eventRepository.getComicsOfEvent(eventW.event.id).collect {
            _eventWrapper.value = eventW.apply {
                this.comics = it.toMutableList()
            }
        }
    }
}
