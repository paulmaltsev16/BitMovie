package com.paulmaltsev.bitmovie.core.models.videos

import com.google.gson.annotations.SerializedName

data class VideoModel(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("key")
    var key: String? = null,
    @SerializedName("site")
    var site: String? = null,
)