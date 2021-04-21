package org.sixelasavir.product.marvelfans.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.sixelasavir.product.marvelfans.Character
import org.sixelasavir.product.marvelfans.api.repositories.CharacterRepository

class CharacterViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    private val _characters: MutableLiveData<List<Character>> = MutableLiveData()
    val characters: LiveData<List<Character>>
        get() = _characters

    init {
        loadCharacters(0)
    }

    fun loadCharacters(offset: Int): Job = viewModelScope.launch {
        characterRepository.getCharacters(offset)
            .collect {
                _characters.value = it
            }
    }
}