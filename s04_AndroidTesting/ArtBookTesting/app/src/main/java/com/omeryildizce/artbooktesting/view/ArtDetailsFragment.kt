package com.omeryildizce.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.omeryildizce.artbooktesting.R
import com.omeryildizce.artbooktesting.databinding.FragmentArtDetailsBinding

class ArtDetailsFragment : Fragment(R.layout.fragment_art_details) {
    private lateinit var binding: FragmentArtDetailsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtDetailsBinding.bind(view)

        binding.artImageView.setOnClickListener {
            val action = ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment()
            Navigation.findNavController(view).navigate(action)

        }


        val callback = object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}