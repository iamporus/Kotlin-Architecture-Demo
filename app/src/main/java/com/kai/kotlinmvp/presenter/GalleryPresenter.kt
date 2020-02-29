package com.kai.kotlinmvp.presenter

import com.kai.kotlinmvp.model.Picture
import com.kai.kotlinmvp.model.PictureSDK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GalleryPresenter()
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
            val pictureList = PictureSDK().getPictureList()

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