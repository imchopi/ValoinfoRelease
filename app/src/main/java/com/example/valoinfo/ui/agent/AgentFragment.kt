package com.example.valoinfo.ui.agent

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
import com.example.valoinfo.data.db.agents.Agent
import com.example.valoinfo.databinding.FragmentAgentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AgentFragment : Fragment() {
    private lateinit var binding: FragmentAgentBinding
    private val viewModel: AgentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAgentBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AgentAdapter(requireContext(), ::toDetail)
        val recyclerView = binding.agents
        recyclerView.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    adapter.submitList(it.agent)
                }
            }
        }
    }

    fun toDetail (agent: Agent) {
       val action = AgentFragmentDirections.actionAgentFragmentToSkillFragment(agent.displayName)
        findNavController().navigate(action)
    }
}