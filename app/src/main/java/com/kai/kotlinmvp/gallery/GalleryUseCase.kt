package com.kai.kotlinmvp.gallery

import com.kai.kotlinmvp.gallery.model.Picture
import com.kai.kotlinmvp.gallery.model.GallerySDK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GalleryUseCase(private val mGallerySDK: GallerySDK)
{
    interface OnGalleryFetchedListener {

        fun onGalleryDataFetched(pictureList: MutableList<Picture> )
        fun onGalleryFetchFailed()
    }

    private val mListeners = mutableSetOf<OnGalleryFetchedListener>()

    fun registerListener(listener: OnGalleryFetchedListener){
        mListeners.add(listener)
    }

    fun unregisterListener(listener: OnGalleryFetchedListener){
        mListeners.remove(listener)
    }

    fun fetchGalleryDataAndNotify()
    {
        CoroutineScope(IO).launch {
            val pictureList = mGallerySDK.getPictureList()

            withContext(Main){
                onResultReceived( pictureList )
            }
        }
    }

    private fun onResultReceived(pictureList: MutableList<Picture>)
    {
        for (listener in mListeners) {

            if (pictureList.size > 0) {
                listener.onGalleryDataFetched(pictureList)
            } else {
                listener.onGalleryFetchFailed()
            }
        }
    }

}