package org.sixelasavir.product.marvelfans.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.viewmodel.ext.android.viewModel
import org.sixelasavir.product.marvelfans.R
import org.sixelasavir.product.marvelfans.databinding.FragmentCharacterBinding
import org.sixelasavir.product.marvelfans.ui.viewmodel.CharacterViewModel

class CharacterFragment : Fragment() {
    private val characterViewModel: CharacterViewModel by viewModel()
    private lateinit var binding: FragmentCharacterBinding
    private var lastTotal: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character, container, false)

        binding.characterVM = characterViewModel
        binding.lifecycleOwner = this

        binding.characterRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context).apply {
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (itemCount != lastTotal && itemCount <= findLastVisibleItemPosition() + 2) {
                            lastTotal = itemCount
                            characterViewModel.loadCharacters(itemCount)
                        }
                    }
                })
            }

            adapter = CharacterAdapter(mutableListOf())
        }
        return binding.root
    }
}
