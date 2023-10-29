package co.aladinjunior.youtube.util

import co.aladinjunior.youtube.main.model.Publisher
import co.aladinjunior.youtube.main.model.PublisherBuilder
import co.aladinjunior.youtube.main.model.Video
import co.aladinjunior.youtube.main.model.VideoBuilder
import java.text.SimpleDateFormat
import java.util.*

fun Date.formatted() : String {
    return SimpleDateFormat("d MM yyyy", Locale("pt", "BR")).format(this)
}

fun String.toDate() : Date{
    return SimpleDateFormat("yyyy-MM-dd", Locale("pt", "BR")).parse(this)!!
}

fun video(block: VideoBuilder.() -> Unit) : Video {
    return VideoBuilder().apply(block).build()
}

fun publisher(block: PublisherBuilder.() -> Unit) : Publisher {
    return PublisherBuilder().apply(block).build()
}

fun videos() : List<Video>{

    return arrayListOf(
        video {
            id = "UVpKBHO2fMg"
            thumbnailUrl = "https://img.youtube.com/vi/UVpKBHO2fMg/maxresdefault.jpg"
            title = "Entrevista com Marlon Wayans | The Noite (14/08/19)"
            publishedAt = "2019-08-15".toDate()
            viewsCount = 742_497
            duration = 1886
            publisher {
                id = "sbtthenoite"
                name = "The Noite com Danilo Gentili"
                pictureProfileUrl =
                    "https://yt3.ggpht.com/a/AGF-l7_3BYlSlp94WOjGe1UECUCdb73qRJVFH_t9Tw=s48-c-k-c0xffffffff-no-rj-mo"
            }
        },
        video {
            id = "PlYUZU0H5go"
            thumbnailUrl = "https://img.youtube.com/vi/PlYUZU0H5go/maxresdefault.jpg"
            title = "LAST CHRISTMAS Official Trailer (2019) Emilia Clarke Movie"
            publishedAt = "2019-08-28".toDate()
            viewsCount = 5_468_366
            duration = 194
            publisher {
                id = "UCpJN7kiUkDrH11p0GQhLyFw"
                name = "Movie Trailer Source"
                pictureProfileUrl =
                    "https://yt3.ggpht.com/a/AGF-l7_Qmltcncwt0z_XzAzjxnuE5gVV9uj7zThg2w=s48-c-k-c0xffffffff-no-rj-mo"
            }
        }
    )

}

