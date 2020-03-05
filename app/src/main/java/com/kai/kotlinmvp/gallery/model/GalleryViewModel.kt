package com.kai.kotlinmvp.gallery.model

import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {
    var mPicturesList: MutableList<Picture> = mutableListOf()
}