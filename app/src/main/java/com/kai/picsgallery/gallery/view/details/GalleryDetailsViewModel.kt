package com.kai.picsgallery.gallery.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kai.picsgallery.gallery.model.Picture

class GalleryDetailsViewModel(val picture: Picture) : ViewModel() {

    private val _selectedPicture = MutableLiveData<Picture>()
    val authorName: LiveData<Picture>
        get() = _selectedPicture

    init {
        _selectedPicture.value = picture
    }


}