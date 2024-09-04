package com.example.starwars

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.starwars.adapter.OnPlayerClickListener
import com.example.starwars.adapter.PlayerAdapter
import com.example.starwars.databinding.ActivityMainBinding
import com.example.starwars.model.uimodel.UIPlayerListItem
import com.example.starwars.network.ApiService
import com.example.starwars.network.RetrofitInstance
import com.example.starwars.repository.StarwarsRepository
import com.example.starwars.viewmodel.PlayerViewModel
import com.example.starwars.viewmodel.PlayerViewModelFactory

class MainActivity : AppCompatActivity(), OnPlayerClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: StarwarsRepository
    private lateinit var apiService: ApiService
    private lateinit var viewModel : PlayerViewModel
    private lateinit var playerAdapter : PlayerAdapter
    private var sortOrder : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.statusBarColor = getColor(R.color.purple)
        setContentView(binding.root)

        apiService = RetrofitInstance.apiService
        repository = StarwarsRepository(apiService)
        viewModel = ViewModelProvider(this, PlayerViewModelFactory(repository))[PlayerViewModel::class.java]

        setObservers()
        setSortClickListener()
    }

    private fun setSortClickListener() {
        binding.btnSort.setOnClickListener{
            sortOrder = playerAdapter.sortByScore(sortOrder)
            Log.d("VISHAL", sortOrder.toString())
        }
    }

    private fun setObservers() {
        viewModel.playerList.observe(this) {
            playerAdapter = PlayerAdapter(it, this)
            binding.rvPlayers.adapter = playerAdapter
        }

        viewModel.matchList.observe(this) {
        }
    }

    override fun onPlayerClick(player : UIPlayerListItem) {
        Toast.makeText(this, "${player.name} is selected", Toast.LENGTH_SHORT).show()

        viewModel.player = player
        viewModel.filterMatchList()
        val fragment = MatchFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        // Check if there are fragments in the backstack
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack() // Remove the top fragment from the backstack
        } else {
            super.onBackPressed() // No fragments in the backstack, so call the default back action
        }
    }


}