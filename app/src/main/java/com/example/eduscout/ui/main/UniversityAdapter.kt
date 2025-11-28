package com.example.eduscout.ui.main
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eduscout.data.model.UniversityEntity
import com.example.eduscout.databinding.ItemUniversityBinding

class UniversityAdapter : RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder>() {

    private var list: List<UniversityEntity> = emptyList()

    fun submitList(newList: List<UniversityEntity>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val binding = ItemUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UniversityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class UniversityViewHolder(private val binding: ItemUniversityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UniversityEntity) {
            binding.tvName.text = item.name
            binding.tvProvince.text = item.province
            binding.tvWeb.text = item.web
        }
    }
}
