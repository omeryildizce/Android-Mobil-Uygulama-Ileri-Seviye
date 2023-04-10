package com.omeryildizce.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.countries.databinding.FragmentCountryBinding
import com.omeryildizce.countries.viewmodel.CountryViewModel


class CountryFragment : Fragment() {
    private lateinit var binding: FragmentCountryBinding
    private var countryUuid = 0
    private lateinit var viewModel :CountryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataRoom()

        arguments?.let { bundle ->
            countryUuid = CountryFragmentArgs.fromBundle(bundle).countryUuid

        }
        observeData()
    }


    private fun observeData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            country ->
            country?.let {
                binding.countryNameTextView.text = it.name
                binding.capitalTextView.text = it.capital
                binding.regionTextView.text = it.region
                binding.currencyTextView.text = it.currency
                binding.languageTextView.text = it.language

            }
        })
    }
}