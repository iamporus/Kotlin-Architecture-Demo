package com.kai.picsgallery.gallery.view.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kai.picsgallery.gallery.model.Picture
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class GalleryDetailsViewModelFactory(private val selectedPicture: Picture) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryDetailsViewModel::class.java)) {
            return GalleryDetailsViewModel(selectedPicture) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}