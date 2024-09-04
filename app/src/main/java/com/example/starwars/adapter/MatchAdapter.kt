package com.example.starwars.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.databinding.MatchItemViewBinding
import com.example.starwars.model.apimodel.MatchList
import com.example.starwars.model.uimodel.MatchResult
import com.example.starwars.model.uimodel.UIMatchList
import com.example.starwars.model.uimodel.UIPlayerListItem

class MatchAdapter(
    private val matchList : UIMatchList,
    private val player : UIPlayerListItem?
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {
    inner class MatchViewHolder(private val binding: MatchItemViewBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("ResourceAsColor")
        fun bind(position: Int) {
            val match = matchList[position]
            binding.tvPlayer1.text = match.player1.name
            binding.tvPlayer2.text = match.player2.name
            binding.tvScore.text = "${match.player1.score} - ${match.player2.score}"

            when(player?.id) {
                match.player1.id -> {
                    when(match.player1.result) {
                        MatchResult.Win -> binding.cardContainer.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.win))
                        MatchResult.Loss -> binding.cardContainer.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.loss))
                        MatchResult.Draw -> binding.cardContainer.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.draw))
                    }
                }
                match.player2.id -> {
                    when(match.player2.result) {
                        MatchResult.Win -> binding.cardContainer.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.win))
                        MatchResult.Loss -> binding.cardContainer.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.loss))
                        MatchResult.Draw -> binding.cardContainer.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.draw))
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = MatchItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(position)
    }
}