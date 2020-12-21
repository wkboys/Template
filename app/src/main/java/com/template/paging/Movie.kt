package com.template.paging

import java.util.*

class Movie {
    @JvmField
    var id: String? = null
    @JvmField
    var title: String? = null
    @JvmField
    var year: String? = null
    @JvmField
    var images: Images? = null

    inner class Images {
        @JvmField
        var small: String? = null
        override fun toString(): String {
            return "Images{" +
                    "small='" + small + '\'' +
                    '}'
        }
    }

    override fun toString(): String {
        return "Movie{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", images=" + images +
                '}'
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Movie) return false
        val movie = o
        return id == movie.id && title == movie.title && year == movie.year && images == movie.images
    }

    override fun hashCode(): Int {
        return Objects.hash(id, title, year, images)
    }
}