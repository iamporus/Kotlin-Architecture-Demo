package com.kai.picsgallery.gallery.usecase

import com.kai.picsgallery.gallery.model.Picture
import com.kai.picsgallery.network.PictureGalleryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GalleryGridUseCase() {

    interface OnGalleryFetchedListener {
        fun onGalleryDataFetched(pictureList: List<Picture>)
        fun onGalleryFetchFailed()
    }

    fun fetchGalleryPicturesAsync(listener: OnGalleryFetchedListener) {

        CoroutineScope(IO).launch {

            val pictureListDeferred = PictureGalleryApi.pictureGalleryService.getGalleryPictures()
            try {
                val picturesList = pictureListDeferred.await()
                withContext(Main) {
                    listener.onGalleryDataFetched(picturesList)
                }
            } catch (e: Exception) {
                listener.onGalleryFetchFailed()
            }
        }
    }

}