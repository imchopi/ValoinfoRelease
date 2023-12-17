package com.example.valoinfo.ui.DetailTierlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.valoinfo.R
import com.example.valoinfo.data.db.agents.Agent
import com.example.valoinfo.databinding.FragmentDetailBinding
import com.example.valoinfo.databinding.FragmentDetailTierlistBinding
import com.example.valoinfo.ui.agent.DetailTierlistAdapter
import com.example.valoinfo.ui.detail.DetailFragmentArgs
import com.example.valoinfo.ui.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailTierlistFragment : Fragment() {
    private lateinit var binding: FragmentDetailTierlistBinding
    private val viewModel: DetailTierlistViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailTierlistBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DetailTierlistAdapter(requireContext())
        val rv = binding.tierlist
        rv.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.topAppBar.setNavigationOnClickListener(){
                    Log.d("Entrando", "BACK")
                    findNavController().popBackStack(R.id.tierlistFragment, false)
                }

                viewModel.tierlistDetail.collect() {
                    adapter.submitList(it.agent)
                    binding.saveTierlist.setOnClickListener {
                        var checkedAgents: List<Agent> = listOf()
                        var nameAgents: List<String> = listOf()
                        val tierlistName = binding.editTextTierlistName.text.toString()

                        if (tierlistName.isEmpty()) {
                            Toast.makeText(view.context, getString(R.string.fill_data), Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        }

                        checkedAgents = adapter.currentList.filter {
                            it.isChecked
                        }

                        if (checkedAgents.size != 5) {
                            Toast.makeText(view.context, getString(R.string.select_five_elements), Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        }

                        nameAgents = checkedAgents.map {
                            it.uuid
                        }

                        viewModel.createTierlist(tierlistName, nameAgents)
                        findNavController().popBackStack(R.id.tierlistFragment, false)
                    }
                }
            }
        }




    }
}