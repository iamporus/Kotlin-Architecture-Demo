package com.kai.kotlinmvp.gallery.usecase

import com.kai.kotlinmvp.gallery.model.Picture
import com.kai.kotlinmvp.gallery.model.GallerySDK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GalleryGridUseCase(private val mGallerySDK: GallerySDK) {

    interface OnGalleryFetchedListener {
        fun onGalleryDataFetched(pictureList: MutableList<Picture>)
        fun onGalleryFetchFailed()
    }

    fun fetchGalleryPicturesAsync(listener: OnGalleryFetchedListener) {

        CoroutineScope(IO).launch {
            val pictureList = mGallerySDK.getPictureList()

            withContext(Main) {
                onResultReceived(pictureList, listener)
            }
        }
    }

    private fun onResultReceived(
        pictureList: MutableList<Picture>,
        listener: OnGalleryFetchedListener
    ) {

        if (pictureList.size > 0) {
            listener.onGalleryDataFetched(pictureList)
        } else {
            listener.onGalleryFetchFailed()
        }
    }

}