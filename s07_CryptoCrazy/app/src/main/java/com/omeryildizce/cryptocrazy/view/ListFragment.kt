package com.omeryildizce.cryptocrazy.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.omeryildizce.cryptocrazy.adapter.RecyclerViewAdapter
import com.omeryildizce.cryptocrazy.databinding.FragmentListBinding
import com.omeryildizce.cryptocrazy.model.CryptoModel
import com.omeryildizce.cryptocrazy.service.CryptoApi
import com.omeryildizce.cryptocrazy.viewmodel.CryptoViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.get

class ListFragment : Fragment(), RecyclerViewAdapter.Listener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private var cryptoAdapter = RecyclerViewAdapter(arrayListOf(), this)
    private   val viewModel by viewModel<CryptoViewModel>()

    /*
    private val api = get<CryptoApi>()
    private val apiLazy by inject<CryptoApi>()
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater,container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        // viewModel =  ViewModelProvider(owner = this).get(CryptoViewModel::class.java)
        viewModel.getDataFromApi()
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.cryptoList.observe(viewLifecycleOwner, Observer { cryptoModel ->
            cryptoModel?.let {
                binding.recyclerView.visibility = View.VISIBLE
                cryptoAdapter = RecyclerViewAdapter(ArrayList(cryptoModel.data ?: arrayListOf()), this@ListFragment)
                binding.recyclerView.adapter = cryptoAdapter
            }
        })

        viewModel.cryptoError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it.data == true){
                    binding.cryptoErrorText.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }else{
                    binding.cryptoErrorText.visibility = View.GONE

                }
            }
        })

        viewModel.cryptoLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                if (isLoading.data == true){
                    binding.cryptoProgressBar.visibility = View.VISIBLE
                    binding.cryptoErrorText.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE

                    binding.cryptoProgressBar.indeterminateTintList = ColorStateList.valueOf(Color.MAGENTA)
                }else{
                    binding.cryptoProgressBar.visibility = View.GONE
                }
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onItemClick(cryptoModel: CryptoModel) {

        Toast.makeText(requireContext(), "Clicked on: ${cryptoModel.currency}", Toast.LENGTH_SHORT).show()
    }
}