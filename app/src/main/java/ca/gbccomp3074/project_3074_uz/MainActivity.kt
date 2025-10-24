package ca.gbccomp3074.project3074.uz // <--- Use this package name (matches the Restaurant.kt location)
import android.content.Intent
import androidx.cardview.widget.CardView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.gbccomp3074.project_3074.uz.Restaurant  // <-- Ensure this import is correct
//import ca.gbccomp3074.project3074.uz.databinding.ActivityMainBinding
import com.example.personalrestaurantguide.databinding.ActivityMainBinding

// Note: You must also have a file named 'RestaurantAdapter.kt' defined.
// Note: You must also have 'DetailsActivity.kt' and 'AddEditActivity.kt' defined.

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val restaurantList = mutableListOf<Restaurant>()
    private lateinit var adapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure you have configured View Binding (or Data Binding) in your build.gradle
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Dummy data
        restaurantList.add(
            Restaurant(
                "Dummy Restaurant 1",
                "123 Main St",
                "123-456-7890",
                "Description",
                "tag1,tag2",
                4.5f
            )
        )
        restaurantList.add(
            Restaurant(
                "Dummy Restaurant 2",
                "456 Elm St",
                "987-654-3210",
                "Another desc",
                "tag3",
                3.0f
            )
        )

        // Setup RecyclerView Adapter and click listener
        adapter = RestaurantAdapter(restaurantList) { restaurant ->
            val intent = Intent(this, DetailsActivity:.java)
            intent.putExtra("restaurant", restaurant)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Setup Floating Action Button (FAB)
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddEditActivity::class.java))
        }

        // Setup Search View
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText.orEmpty())
                return true
            }
        })

        // Example for About Button (if included in the XML)
        /*
        binding.btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
        */
    }

    override fun onResume() {
        super.onResume()
        // If you need to refresh the list of restaurants when returning from another activity,
        // you would reload the data here and call adapter.notifyDataSetChanged() or similar.
    }
}