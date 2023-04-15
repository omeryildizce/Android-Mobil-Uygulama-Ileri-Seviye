package com.omeryildizce.artbooktesting.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.omeryildizce.artbooktesting.R
import com.omeryildizce.artbooktesting.databinding.FragmentArtDetailsBinding
import com.omeryildizce.artbooktesting.util.Status
import com.omeryildizce.artbooktesting.viewmodel.ArtViewModel
import javax.inject.Inject

class ArtDetailsFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.fragment_art_details) {
    private lateinit var binding: FragmentArtDetailsBinding
    lateinit var viewModel: ArtViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)
        binding = FragmentArtDetailsBinding.bind(view)
        subscribeToObservers()
        binding.artImageView.setOnClickListener {
            val action = ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment()
            Navigation.findNavController(view).navigate(action)

        }


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        binding.saveButton.setOnClickListener {
            viewModel.makeArt(
                binding.nameText.text.toString(),
                binding.artistText.text.toString(),
                binding.yearText.text.toString()
            )
        }
    }
    private fun subscribeToObservers(){
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer {url ->
            binding?.let {
                glide.load(url).into(it.artImageView )
            }
        })
        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer{
            when(it.status){
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Succes", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertArtMsg()
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING->{

                }
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }
}