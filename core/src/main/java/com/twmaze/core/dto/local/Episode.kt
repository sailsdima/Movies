package com.twmaze.core.dto.local

data class Episode(
    val id: Long,
    val url: String,
    val name: String,
    val type: String,
    val season: Int,
    val number: Int,
    val runtime: Int,
    val rating: Rating,
    val image: Image,
    val summary: String,
)