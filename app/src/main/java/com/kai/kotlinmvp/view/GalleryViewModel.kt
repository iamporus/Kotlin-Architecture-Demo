package com.kai.kotlinmvp.view

import androidx.lifecycle.ViewModel
import com.kai.kotlinmvp.model.Picture

class GalleryViewModel : ViewModel(){
    var mPicturesList: MutableList<Picture> = mutableListOf()
}