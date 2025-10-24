package com.example.responsi1mobileh1d023086

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ifunsoedmobile.databinding.ActivityHeadCoachBinding
import com.example.responsi1mobileh1d023065.viewmodel.MainViewModel

class HeadCoachActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHeadCoachBinding
    private val viewModel : MainViewModel by viewModels()

    private val CLUB_ID = 322

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHeadCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        viewModel.fetchCoach(CLUB_ID)
    }

    private fun observeViewModel() {
        viewModel.coach.observe(this) { coach ->
            coach?.let {
                binding.tvHeadCoachName.text = it.name
                binding.tvHeadCoachDateOfBirth.text = it.dateOfBirth
                binding.tvHeadCoachNationality.text = it.nationality
            }
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

    }

}
