package ca.gbccomp3074.project_3074.uz
import androidx.cardview.widget.CardView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import ca.gbccomp3074.project_3074.uz.databinding.ActivityAddEditBinding
import com.example.personalrestaurantguide.databinding.ActivityAddEditBinding

class AddEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // If editing, get extra and populate fields
        val restaurant = intent.getSerializableExtra("restaurant") as? Restaurant
        if (restaurant != null) {
            binding.etName.setText(restaurant.name)
            binding.etAddress.setText(restaurant.address)
            binding.etPhone.setText(restaurant.phone)
            binding.etDescription.setText(restaurant.description)
            binding.etTags.setText(restaurant.tags)
            binding.rbRating.rating = restaurant.rating
        }

        binding.btnSave.setOnClickListener {
            // For prototype, just finish. In full, save to list or DB
            finish()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}