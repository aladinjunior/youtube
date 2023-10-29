package co.aladinjunior.youtube.main.model

import java.util.*

class VideoBuilder {
    var id: String = ""
    var thumbnailUrl: String = ""
    var title: String = ""
    var viewsCount: Long = 0
    var publishedAt: Date = Date()
    var viewsCountLabel: String = ""
    var duration: Int = 0
    var videoUrl: String = ""
    var publisher: Publisher = PublisherBuilder().build()

    fun build(): Video {
        return Video(
            id,
            thumbnailUrl,
            title,
            viewsCount,
            publishedAt,
            viewsCountLabel,
            duration,
            videoUrl,
            publisher
        )
    }
}