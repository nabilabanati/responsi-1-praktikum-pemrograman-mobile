package com.example.responsi1mobileh1d023086

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ifunsoedmobile.R
import com.example.ifunsoedmobile.databinding.ActivityMainBinding
import com.example.responsi1mobileh1d023065.data.model.TeamSquad


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
        initListener()
        }

    private fun initLayout(){
        binding.layoutClub.let{
            it.imgIcon.setImageResource(R.drawable.ic_football)
            it.tvLayout.setText(R.string.history)
        }

        binding.layoutCoach.let{
            it.imgIcon.setImageResource(R.drawable.ic_coach)
            it.tvLayout.setText(R.string.head_coach)
        }

        binding.layoutTeam.let{
            it.imgIcon.setImageResource(R.drawable.ic_team)
            it.tvLayout.setText(R.string.team_squad)
        }
    }

    private fun initListener(){
        binding.layoutClub.root.setOnClickListener {
            startActivity(Intent(this, ClubHistoryActivity::class.java))
            startActivity(intent)
        }

        binding.layoutCoach.root.setOnClickListener {
            startActivity(Intent(this, HeadCoachActivity::class.java))
            startActivity(intent)
        }

        binding.layoutTeam.root.setOnClickListener {
            startActivity(Intent(this, TeamSquadActivity::class.java))
            startActivity(intent)
        }
    }
}