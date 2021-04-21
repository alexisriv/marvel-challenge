package org.sixelasavir.product.marvelfans.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.koin.android.viewmodel.ext.android.viewModel
import org.sixelasavir.product.marvelfans.Character
import org.sixelasavir.product.marvelfans.R
import org.sixelasavir.product.marvelfans.databinding.ActivityCharacterDetailBinding
import org.sixelasavir.product.marvelfans.ui.viewmodel.CharacterDetailViewModel

class CharacterDetailActivity : AppCompatActivity() {

    private val characterDetailViewModel: CharacterDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityCharacterDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_character_detail)
        setSupportActionBar(binding.toolbar)
        binding.characterDetailVM = characterDetailViewModel
        binding.lifecycleOwner = this
        intent?.extras?.getParcelable<Character>("key_character")?.let { character ->
            characterDetailViewModel.loadComics(character.id)
            characterDetailViewModel.setData(character)
        }
    }
}
