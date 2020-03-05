package com.kai.kotlinmvp.view.gallerygrid

import androidx.lifecycle.ViewModel
import com.kai.kotlinmvp.gallery.model.Picture

class GalleryViewModel : ViewModel(){
    var mPicturesList: MutableList<Picture> = mutableListOf()
}