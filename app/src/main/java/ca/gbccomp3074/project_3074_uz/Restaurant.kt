package ca.gbccomp3074.project_3074.uz

import java.io.Serializable

data class Restaurant(
    val name: String,
    val address: String,
    val phone: String,
    val description: String,
    val tags: String,
    val rating: Float
) : Serializable