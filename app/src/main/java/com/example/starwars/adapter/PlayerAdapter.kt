package com.example.starwars.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.starwars.databinding.PlayerItemViewBinding
import com.example.starwars.model.uimodel.UIPlayerList
import com.example.starwars.model.uimodel.UIPlayerListItem

interface OnPlayerClickListener {
    fun onPlayerClick(player : UIPlayerListItem)
}

class PlayerAdapter(
    private val playerList : UIPlayerList,
    private val clickListener : OnPlayerClickListener
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(private val binding: PlayerItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, clickListener: OnPlayerClickListener) {
            val player = playerList[position]

            binding.tvName.text = player.name
            binding.tvValue.text = player.point.toString()
            Glide.with(binding.root.rootView)
                .load(player.icon.toString().replace("http", "https"))
                .into(binding.logo)

            binding.root.setOnClickListener{
                clickListener.onPlayerClick(player)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = PlayerItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(position, clickListener)
    }

    fun sortByScore(sortOrder : Boolean) : Boolean {
        if(sortOrder) playerList.sortBy { it.point }
        else playerList.sortByDescending { it.point }

        notifyDataSetChanged()
        return !sortOrder
    }

}