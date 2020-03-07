package com.kai.picsgallery.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kai.picsgallery.gallery.model.GallerySDK
import com.kai.picsgallery.gallery.model.Picture
import com.kai.picsgallery.gallery.usecase.GalleryGridUseCase

class GalleryViewModel : ViewModel(), GalleryGridUseCase.OnGalleryFetchedListener {

    var mPicturesList = MutableLiveData<MutableList<Picture>>()

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
        GalleryGridUseCase(GallerySDK()).fetchGalleryPicturesAsync(this)
    }

    override fun onGalleryDataFetched(pictureList: MutableList<Picture>) {

        mPicturesList.value = pictureList
        _bIsFetching.value = false
    }

    override fun onGalleryFetchFailed() {
        _bIsFetching.value = false
        _bFetchFailed.value = true
    }

    override fun onCleared() {
        super.onCleared()
        mPicturesList.value?.clear()
    }

    fun onPictureClicked(pictureItem: Picture) {
        _bNavigateToPictureDetails.value = pictureItem
    }

    fun onNavigatedToPictureDetails() {
        _bNavigateToPictureDetails.value = null
    }

}