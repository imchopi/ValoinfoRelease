package com.example.valoinfo.ui.agent

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.example.valoinfo.data.db.agents.Agent
import com.example.valoinfo.data.db.tierlist.Tierlist
import com.example.valoinfo.databinding.AgentItemBinding
import com.example.valoinfo.databinding.TierlistItemBinding

class DetailTierlistAdapter(
    private val context: Context
): ListAdapter<Agent, DetailTierlistAdapter.DetailTierlistViewHolder>(DetailTierlistDiffCallback) {
    inner class DetailTierlistViewHolder(private val binding: TierlistItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (agent: Agent) {
            binding.tierlistName.text = agent.displayName
            binding.check.isChecked = agent.isChecked

            binding.check.setOnClickListener{
                agent.isChecked = binding.check.isChecked
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailTierlistViewHolder = DetailTierlistViewHolder(TierlistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: DetailTierlistViewHolder, position: Int) = holder.bind(getItem(position))

    private object DetailTierlistDiffCallback: DiffUtil.ItemCallback<Agent>() {
        override fun areItemsTheSame(oldItem: Agent, newItem:Agent) = oldItem.displayName == newItem.displayName
        override fun areContentsTheSame(oldItem: Agent, newItem: Agent) = oldItem == newItem
    }

}