package com.kwabenaberko.rickandmorty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kwabenaberko.rickandmorty.domain.model.Character
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val characters = mutableListOf<Character>()

    class CharacterViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: Character){
            itemView.textView.text = character.name
            itemView.imageView.load(character.imageUrl){
                crossfade(true)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    fun refill(characters: List<Character>){
        this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

}