package org.sixelasavir.product.marvelfans.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.sixelasavir.product.marvelfans.Event
import org.sixelasavir.product.marvelfans.api.repositories.EventRepository

class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {
    private val _events: MutableLiveData<List<Event>> = MutableLiveData()
    val events: LiveData<List<Event>>
        get() = _events

    init {
        loadEvents(0)
    }

    fun loadEvents(offset: Int): Job = viewModelScope.launch {
        eventRepository.getEvents(offset).collect {
            _events.value = it
        }
    }
}
