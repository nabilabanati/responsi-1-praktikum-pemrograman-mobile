package com.example.responsi1mobileh1d023065.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ifunsoedmobile.databinding.FragmentPlayerDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayerDetailFragment : BottomSheetDialogFragment() {
    private var playerName: String? = null
    private var dateOfBirth: String? = null
    private var nationality: String? = null
    private var position: String? = null
    private var _binding: FragmentPlayerDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playerName = it.getString(ARG_SQUAD_NAME)
            dateOfBirth = it.getString(ARG_DATE_OF_BIRTH)
            nationality = it.getString(ARG_NATIONALITY)
            position = it.getString(ARG_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playerName.text = playerName
        binding.playerDateOfBirth.text = "Date of Birth: $dateOfBirth"
        binding.playerNationality.text = "Nationality: $nationality"
        binding.playerPosition.text = "Position: $position"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_SQUAD_NAME = "squad_name"
        private const val ARG_DATE_OF_BIRTH = "date_of_birth"
        private const val ARG_NATIONALITY = "nationality"
        private const val ARG_POSITION = "position"

        fun newInstance(
            squadName: String,
            dateOfBirth: String,
            nationality: String,
            position: String
        ) = PlayerDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_SQUAD_NAME, squadName)
                putString(ARG_DATE_OF_BIRTH, dateOfBirth)
                putString(ARG_NATIONALITY, nationality)
                putString(ARG_POSITION, position)
            }
        }
    }
}