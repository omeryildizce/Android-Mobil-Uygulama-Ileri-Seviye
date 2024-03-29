package com.omeryildizce.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.omeryildizce.myapplication.databinding.ItemCountryBinding
import com.omeryildizce.myapplication.model.Country
import com.omeryildizce.myapplication.view.FeedFragmentDirections


class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>()  {
    class CountryViewHolder(val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.country = countryList.get(position)

        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            action.countryUuid = countryList.get(position).uuid
            Navigation.findNavController(it).navigate(action)
        }


        /*
            holder.binding.name.text = countryList.get(position).name
            holder.binding.region.text = countryList.get(position).region
            holder.binding.imageView.downloadFromUrl(
            countryList.get(position).imageUrl,
            placeHolderProgressBar(holder.binding.root.context)
            )
        */

    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()


    }


}