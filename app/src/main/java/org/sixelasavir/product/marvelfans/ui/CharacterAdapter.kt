package org.sixelasavir.product.marvelfans.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sixelasavir.product.marvelfans.Character
import org.sixelasavir.product.marvelfans.R
import org.sixelasavir.product.marvelfans.databinding.ItemCharacterBinding

class CharacterAdapter(val characters: MutableList<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_character,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindData(characters[position])
    }

    inner class CharacterViewHolder(private val itemCharacterBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(itemCharacterBinding.root) {

        fun bindData(character: Character) {
            itemCharacterBinding.itemCardView.animation = AnimationUtils.loadAnimation(itemView.context, R.anim.zoom_in)
            itemCharacterBinding.character = character
        }
    }
}
