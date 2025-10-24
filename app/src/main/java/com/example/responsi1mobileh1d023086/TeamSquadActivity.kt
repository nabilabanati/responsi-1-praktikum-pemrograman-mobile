package com.example.responsi1mobileh1d023086

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ifunsoedmobile.databinding.ActivityTeamSquadBinding
import com.example.responsi1mobileh1d023065.data.model.TeamSquad
import com.example.responsi1mobileh1d023065.viewmodel.MainViewModel
import com.example.responsi1mobileh1d023086.adapter.TeamSquadAdapter

class TeamSquadActivity : AppCompatActivity(), TeamSquadAdapter.OnSquadClickListener {
    private lateinit var binding: ActivityTeamSquadBinding
    private lateinit var teamSquadAdapter: TeamSquadAdapter
    private val viewModel: MainViewModel by viewModels()
    private val CLUB_ID = 322

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamSquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()

        viewModel.fetchSquad(CLUB_ID)
    }

    private fun setupRecyclerView() {
        teamSquadAdapter = TeamSquadAdapter(this)
        binding.recyclerView.apply {
            adapter = teamSquadAdapter
            layoutManager = LinearLayoutManager(this@TeamSquadActivity)
        }
    }

    private fun observeViewModel() {
        viewModel.squad.observe(this) { squad ->
            teamSquadAdapter.submitList(squad)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.recyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onSquadClick(team: TeamSquad) {
//        SquadDetailFragment.newInstance(
//            squadName = squad.name,
//            dateOfBirth = squad.dateOfBirth,
//            nationality = squad.nationality,
//            position = squad.position
//        ).show(supportFragmentManager, SquadDetailFragment::class.java.simpleName)
    }
}
