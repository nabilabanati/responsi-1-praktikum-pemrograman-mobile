package com.example.responsi1mobileh1d023086.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ifunsoedmobile.R
import com.example.ifunsoedmobile.databinding.ListTeamBinding
import com.example.responsi1mobileh1d023065.data.model.TeamSquad

class TeamSquadAdapter(private val listener: OnSquadClickListener) :
    ListAdapter<TeamSquad, TeamSquadAdapter.ViewHolder>(DiffCallback) {

    interface OnSquadClickListener {
        fun onSquadClick(team: TeamSquad)
    }

    class ViewHolder(
        private val binding: ListTeamBinding,
        private val listener: OnSquadClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(team: TeamSquad) {
            binding.tvTeamName.text = team.name
            binding.teamNationality.text = team.nationality


            binding.cardTeamSquad.setOnClickListener {
                listener.onSquadClick(team)
            }

            val positionColor = getPositionColor(binding.root.context, team.position)
            binding.cardTeamSquad.setCardBackgroundColor(positionColor)
        }

        private fun getPositionColor(context: Context, position: String?): Int {
            val safePosition = position.orEmpty()

            val colorResId = when {
                safePosition.contains("Goalkeeper", ignoreCase = true) -> R.color.goalkeeper

                safePosition.contains("Back", ignoreCase = true) ||
                        safePosition.contains("Defender", ignoreCase = true) ||
                        safePosition.contains("Defence", ignoreCase = true) -> R.color.defender

                safePosition.contains("Midfield", ignoreCase = true) -> R.color.midfielder

                safePosition.contains("Forward", ignoreCase = true) ||
                        safePosition.contains("Winger", ignoreCase = true) ||
                        safePosition.contains("Offence", ignoreCase = true) -> R.color.forward

                else -> R.color.white
            }
            return ContextCompat.getColor(context, colorResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListTeamBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TeamSquad>() {
        override fun areItemsTheSame(oldItem: TeamSquad, newItem: TeamSquad) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TeamSquad, newItem: TeamSquad) = oldItem == newItem
    }
}