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
import org.sixelasavir.product.marvelfans.api.repositories.CharacterRepository

class CharacterDetailViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    fun setData(character: Character) {
        _thumbnail.value = character.thumbnail
        _content.value = character.description
    }

    fun loadComics(id: Long) {
        viewModelScope.launch {
            characterRepository.getComicsOfCharacter(id).collect {
                _comics.value = it
            }
        }
    }

    private val _thumbnail: MutableLiveData<Thumbnail> = MutableLiveData()
    val thumbnail: LiveData<Thumbnail>
        get() = _thumbnail

    private val _content: MutableLiveData<String> = MutableLiveData()
    val content: LiveData<String>
        get() = _content

    private val _comics: MutableLiveData<List<Comic>> = MutableLiveData()
    val comics: LiveData<List<Comic>>
        get() = _comics
}
