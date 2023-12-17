package com.example.valoinfo.ui.tierlist

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
import com.example.valoinfo.databinding.TierlistItemNoCheckboxBinding

class TierlistAdapter(
    private val context: Context,
    val onClick:((Tierlist) -> Unit)
) : ListAdapter<Tierlist, TierlistAdapter.TierlistViewHolder>(TierlistDiffCallback) {
    inner class TierlistViewHolder(private val binding: TierlistItemNoCheckboxBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (tierlist: Tierlist) {
            binding.tierlistName.text = tierlist.displayName
            binding.root.setOnClickListener {
                onClick(tierlist)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TierlistAdapter.TierlistViewHolder = TierlistViewHolder(TierlistItemNoCheckboxBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: TierlistAdapter.TierlistViewHolder, position: Int) = holder.bind(getItem(position))


    private object TierlistDiffCallback:DiffUtil.ItemCallback<Tierlist>() {
        override fun areItemsTheSame(oldItem: Tierlist, newItem:Tierlist) = oldItem.displayName == newItem.displayName
        override fun areContentsTheSame(oldItem: Tierlist, newItem: Tierlist) = oldItem == newItem
    }

}