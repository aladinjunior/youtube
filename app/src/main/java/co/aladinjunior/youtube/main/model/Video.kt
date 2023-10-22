package co.aladinjunior.youtube.main.model

import java.util.*

data class Video(
    val id: String,
    val thumbnailUrl: String,
    val title: String,
    val viewsCount: Long,
    val publishedAt: Date,
    val viewsCountLabel: String,
    val duration: Int,
    val videoUrl: String,
    val publisher: Publisher
)

