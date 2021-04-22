package org.sixelasavir.product.marvelfans.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.sixelasavir.product.marvelfans.Character
import org.sixelasavir.product.marvelfans.Comic
import org.sixelasavir.product.marvelfans.Thumbnail
import org.sixelasavir.product.marvelfans.repositories.CharacterRepository

class CharacterDetailViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    fun setData(character: Character) {
        _character.value = character
    }

    fun loadComics(id: Long) {
        viewModelScope.launch {
            characterRepository.getComicsOfCharacter(id).collect {
                _comics.value = it
            }
        }
    }

    private val _character: MutableLiveData<Character> = MutableLiveData()
    val character: LiveData<Character>
        get() = _character

    private val _comics: MutableLiveData<List<Comic>> = MutableLiveData()
    val comics: LiveData<List<Comic>>
        get() = _comics
}
