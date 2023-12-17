package com.example.valoinfo.ui.allTier

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.valoinfo.R
import com.example.valoinfo.databinding.FragmentAllBinding
import com.example.valoinfo.ui.tierlist.TierlistViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllTierFragment : Fragment(){
    private lateinit var binding: FragmentAllBinding
    private val viewModel: AllTierViewModel by viewModels()
    private val viewModelTierlist: TierlistViewModel by viewModels()
    val args: AllTierFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnShare: ImageButton = view.findViewById(R.id.btnShare)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.topAppBar.setNavigationOnClickListener(){
                    findNavController().popBackStack(R.id.tierlistFragment, false)
                }
                viewModel.getAgent(args.id)
                btnShare.setOnClickListener {
                    shareContent()
                }
                viewModel.allTier.collect { agentImage ->
                    agentImage.agents.forEachIndexed { index, agent ->
                        val imageView = when (index) {
                            0 -> binding.imageViewSkill1
                            1 -> binding.imageViewSkill2
                            2 -> binding.imageViewSkill3
                            3 -> binding.imageViewSkill4
                            4 -> binding.imageViewSkill5
                            else -> return@forEachIndexed
                        }
                        imageView.load(agent.displayIcon)
                    }
                }
            }
        }
    }

    private fun shareContent() {
        val agents = viewModel.allTier.value.agents.joinToString(", ") {
                agent -> agent.displayName
        }
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Agents: $agents")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}