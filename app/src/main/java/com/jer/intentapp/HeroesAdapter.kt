package com.jer.intentapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jer.intentapp.databinding.ItemHeroesBinding

class HeroesAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<HeroesAdapter.ItsViewHolder>() {

    class ItsViewHolder(val binding: ItemHeroesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItsViewHolder {
        var itsBinding = ItemHeroesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItsViewHolder(itsBinding)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: ItsViewHolder, position: Int) {
        val (nama, deskripsi, foto) = listHero[position]
        holder.binding.itemNama.text = nama
        holder.binding.itemDeskripsi.text = deskripsi
        holder.binding.photoProfile

        Glide.with(holder.itemView.context)
            .load(foto)
            .into(holder.binding.photoProfile)

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "You choose " + listHero[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
        }
    }


}