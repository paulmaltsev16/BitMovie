package com.paulmaltsev.bitmovie.core.models.videos

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    var id: String?,
    @SerializedName("results")
    var videos: ArrayList<VideoModel>
)