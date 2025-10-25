package ca.gbccomp3074.project_3074_uz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.layout.layout
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.text.lowercase

class RestaurantAdapter(
    private var restaurantList: MutableList<Restaurant>,
    private val onItemClicked: (Restaurant) -> Unit
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    // A copy of the original list to be used for filtering
    private var restaurantListFull: List<Restaurant> = restaurantList.toList()

    // This class holds the views for each item in the list
    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textViewRestaurantName) // Make sure this ID exists in your item layout
        val addressTextView: TextView = itemView.findViewById(R.id.textViewRestaurantAddress) // Make sure this ID exists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        // Inflate the layout for a single restaurant item
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false) // Ensure you have 'item_restaurant.xml'
        return RestaurantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentRestaurant = restaurantList[position]

        // Set the data to the views
        holder.nameTextView.text = currentRestaurant.name
        holder.addressTextView.text = currentRestaurant.address

        // Set the click listener for the item
        holder.itemView.setOnClickListener {
            onItemClicked(currentRestaurant)
        }
    }

    override fun getItemCount() = restaurantList.size

    // This function will filter the list based on user search input
    fun filter(query: String) {
        val filteredList = mutableListOf<Restaurant>()

        if (query.isEmpty()) {
            filteredList.addAll(restaurantListFull)
        } else {
            val filterPattern = query.lowercase(Locale.getDefault()).trim()
            for (item in restaurantListFull) {
                // You can expand this to search by tags, address, etc.
                if (item.name.lowercase(Locale.getDefault()).contains(filterPattern)) {
                    filteredList.add(item)
                }
            }
        }

        restaurantList = filteredList
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}
