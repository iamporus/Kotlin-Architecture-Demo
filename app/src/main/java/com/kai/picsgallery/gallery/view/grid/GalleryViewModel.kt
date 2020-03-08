package com.kai.picsgallery.gallery.view.grid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kai.picsgallery.gallery.model.Picture
import com.kai.picsgallery.gallery.usecase.GalleryGridUseCase

class GalleryViewModel : ViewModel(), GalleryGridUseCase.OnGalleryFetchedListener {

    var mPicturesList = MutableLiveData<List<Picture>>()

    private var _bIsFetching = MutableLiveData<Boolean>()
    val bIsFetching: LiveData<Boolean>
        get() = _bIsFetching

    private var _bFetchFailed = MutableLiveData<Boolean>()
    val bFetchFailed: LiveData<Boolean>
        get() = _bFetchFailed

    private var _bNavigateToPictureDetails = MutableLiveData<Picture>()
    val bNavigateToDetailsEvent: LiveData<Picture>
        get() = _bNavigateToPictureDetails

    init {
        _bIsFetching.value = true
        _bFetchFailed.value = false
        GalleryGridUseCase().fetchGalleryPicturesAsync(this)
    }

    override fun onGalleryDataFetched(pictureList: List<Picture>) {

        mPicturesList.value = pictureList
        _bIsFetching.value = false
    }

    override fun onGalleryFetchFailed() {
        _bIsFetching.value = false
        _bFetchFailed.value = true
    }

    fun onPictureClicked(pictureItem: Picture) {
        _bNavigateToPictureDetails.value = pictureItem
    }

    fun onNavigatedToPictureDetails() {
        _bNavigateToPictureDetails.value = null
    }

}