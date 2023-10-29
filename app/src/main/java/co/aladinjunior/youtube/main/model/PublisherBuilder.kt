package co.aladinjunior.youtube.main.model

class PublisherBuilder {
    var id: String = ""
    var name: String = ""
    var pictureProfileUrl: String = ""

    fun build() : Publisher {
        return Publisher(id, name, pictureProfileUrl)
    }
}