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
import com.example.valoinfo.databinding.AgentItemBinding

class AgentAdapter(
    private val context: Context,
    val onClick:((Agent) -> Unit)
    ) : ListAdapter<Agent, AgentAdapter.AgentViewHolder>(AgenteDiffCallback) {
    inner class AgentViewHolder(private val binding: AgentItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (agent: Agent) {
            binding.agentName.text = agent.displayName
            val agentImage = ImageRequest.Builder(context)
                .data(agent.displayIcon)
                .crossfade(true)
                .target(binding.agentImage)
                .build()
            context.imageLoader.enqueue(agentImage)
            binding.root.setOnClickListener {
                onClick(agent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AgentAdapter.AgentViewHolder = AgentViewHolder(AgentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: AgentAdapter.AgentViewHolder, position: Int) = holder.bind(getItem(position))


    private object AgenteDiffCallback:DiffUtil.ItemCallback<Agent>() {
        override fun areItemsTheSame(oldItem: Agent, newItem: Agent) = oldItem.uuid == newItem.uuid
        override fun areContentsTheSame(oldItem: Agent, newItem: Agent) = oldItem == newItem
    }

}