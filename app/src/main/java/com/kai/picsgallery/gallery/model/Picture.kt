package com.kai.picsgallery.gallery.model

import com.squareup.moshi.Json

data class Picture(
    @Json(name = "id") val authorId: Int,
    @Json(name = "author") val authorName: String
)