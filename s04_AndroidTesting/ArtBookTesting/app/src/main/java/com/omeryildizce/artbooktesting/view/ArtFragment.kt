package com.omeryildizce.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.omeryildizce.artbooktesting.R
import com.omeryildizce.artbooktesting.databinding.FragmentArtsBinding

class ArtFragment : Fragment(R.layout.fragment_arts) {
    private lateinit var binding : FragmentArtsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtsBinding.bind(view)

        binding.fab.setOnClickListener {
            val action = ArtFragmentDirections.actionArtFragmentToArtDetailsFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}