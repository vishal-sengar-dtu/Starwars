package com.example.starwars

import android.content.Context
import android.os.Binder
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.starwars.adapter.MatchAdapter
import com.example.starwars.databinding.FragmentMatchBinding
import com.example.starwars.databinding.PlayerItemViewBinding
import com.example.starwars.model.uimodel.UIPlayerListItem
import com.example.starwars.viewmodel.PlayerViewModel

class MatchFragment : Fragment() {
    private lateinit var binding : FragmentMatchBinding
    private lateinit var viewModel : PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[PlayerViewModel::class.java]
        binding.toolbarTitle.text = viewModel.player?.name

        inflatePlayerMatches()
        binding.btnBack.setOnClickListener{
            if (childFragmentManager.backStackEntryCount > 0) {
                childFragmentManager.popBackStack()
            }
        }

        return binding.root
    }

    private fun inflatePlayerMatches() {
        viewModel.matchList.observe(viewLifecycleOwner) {
            binding.rvMatches.adapter = MatchAdapter(it, viewModel.player)
        }
    }

}