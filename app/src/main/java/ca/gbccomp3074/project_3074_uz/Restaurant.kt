package ca.gbccomp3074.project_3074_uz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val name: String,
    val address: String,
    val phone: String,
    val description: String,
    val tags: String,
   val rating: Float
) : Parcelable
