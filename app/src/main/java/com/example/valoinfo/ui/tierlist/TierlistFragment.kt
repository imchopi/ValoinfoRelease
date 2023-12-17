package com.example.valoinfo.ui.tierlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.valoinfo.data.db.tierlist.Tierlist
import com.example.valoinfo.databinding.FragmentTierlistBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TierlistFragment : Fragment() {
    private lateinit var binding: FragmentTierlistBinding
    private val viewModel: TierlistViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTierlistBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TierlistAdapter(requireContext(), ::toDetail)
        val recyclerView = binding.tierlistWithoutCheckbox
        recyclerView.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tierlistUiState.collect {
                    adapter.submitList(it.tierlist)
                }
            }
        }
        binding.createTierlist.setOnClickListener {
            toAdd()
        }
    }

    fun toAdd() {
        val action = TierlistFragmentDirections.actionTierlistFragmentToDetailTierlistFragment()
        findNavController().navigate(action)
    }

    fun toDetail(tierlist: Tierlist) {
        val action = TierlistFragmentDirections.actionTierlistFragmentToAllTierFragment(tierlist.id)
        findNavController().navigate(action)
    }
}
