package ca.gbccomp3074.project_3074.uz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.gbccomp3074.project_3074.uz.databinding.ItemRestaurantBinding

private val ItemRestaurantBinding.tvName: Any

class `RestaurantAdapter.kt`(
    private var restaurants: List<Restaurant>,
    private val onClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<`RestaurantAdapter.kt`.ViewHolder>() {

    private var filteredList = restaurants.toMutableList()

    class ViewHolder(private val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant, onClick: (Restaurant) -> Unit) {
            binding.tvName.text = restaurant.name
            binding.tvAddress.text = restaurant.address
            binding.ratingBar.rating = restaurant.rating
            binding.root.setOnClickListener { onClick(restaurant) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredList[position], onClick)
    }

    override fun getItemCount() = filteredList.size

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            restaurants.toMutableList()
        } else {
            restaurants.filter {
                it.name.contains(query, ignoreCase = true) || it.tags.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }

    fun bind(restaurant: Restaurant, onClick: (Restaurant) -> Unit) {
        binding.txtName.text = restaurant.name
        binding.txtCuisine.text = restaurant.cuisine // Add cuisine field to Restaurant class if needed
        binding.txtLocation.text = restaurant.address
        binding.root.setOnClickListener { onClick(restaurant) }
    }
}