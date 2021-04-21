package org.sixelasavir.product.marvelfans

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

data class Response<T>(val code: Int, val data: Data<T>)
data class Data<T>(val total: Long, val results: List<T>)

@Parcelize
data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
) : Parcelable

@Parcelize
data class Comic(
    val id: Long,
    val title: String,
    val dates: MutableList<DateComic>
) : Parcelable

@Parcelize
data class DateComic(
    val type: String,
    val date: Date
) : Parcelable

@Parcelize
data class Event(
    val id: Long,
    val title: String,
    val thumbnail: Thumbnail,
    val start: String?,
    val end: String?
) : Parcelable

class EventWrapper(
    var event: Event,
    var comics: MutableList<Comic>? = null,
    var isExpanded: Boolean = false
)

@Parcelize
data class Thumbnail(
    val path: String,
    val extension: String
) : Parcelable
