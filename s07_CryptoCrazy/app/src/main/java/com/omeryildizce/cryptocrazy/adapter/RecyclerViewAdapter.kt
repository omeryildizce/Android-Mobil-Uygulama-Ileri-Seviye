package com.omeryildizce.cryptocrazy.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omeryildizce.cryptocrazy.databinding.RecyclerRowBinding
import com.omeryildizce.cryptocrazy.model.CryptoModel

class RecyclerViewAdapter(
    private val cryptoList: ArrayList<CryptoModel>,
    private val listener: Listener
) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }
    private val colors:Array<String> =  arrayOf("#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#FF00FF", "#00FFFF", "#C0C0C0", "#808080", "#800000", "#008000", "#000080", "#FFA500", "#800080", "#008080", "#000000", "#FFC0CB", "#FFD700", "#FF69B4", "#CD5C5C", "#F08080", "#E9967A", "#FA8072", "#FFE4C4", "#8B0000", "#BC8F8F", "#B22222", "#FF6347", "#FF4500", "#FF8C00", "#D2691E", "#FF7F50", "#FFA07A", "#20B2AA", "#00CED1", "#66CDAA", "#3CB371", "#2E8B57", "#008080", "#6A5ACD", "#9370DB", "#7B68EE")
    class RowHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
            holder.itemView.setOnClickListener {
                listener.onItemClick(cryptoList.get(position))
            }

        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % colors.size]))
        holder.binding.cryptoNameText.text = cryptoList.get(position).currency
        holder.binding.cryptoPriceText.text = cryptoList.get(position).price
    }

}