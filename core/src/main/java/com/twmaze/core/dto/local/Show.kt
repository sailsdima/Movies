package com.twmaze.core.dto.local

data class Show(
    val id: Long,
    val url: String,
    val name: String,
    val type: String,
    val genres: List<String>,
    val language: String,
    val status: String,
    val runtime: Int,
    val averageRuntime: Int,
    val premiered: String?,
    val ended: String?,
    val rating: Rating,
    val image: Image,
    val summary: String,
)