package com.kai.kotlinmvp.view.gallerygrid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kai.kotlinmvp.gallery.GalleryUseCase
import com.kai.kotlinmvp.gallery.model.GallerySDK
import com.kai.kotlinmvp.gallery.model.Picture

class GalleryGridActivity : AppCompatActivity(), GalleryView.Listener,
    GalleryUseCase.OnGalleryFetchedListener {

    private lateinit var mGalleryView: GalleryView
    private lateinit var mGalleryViewModel: GalleryViewModel
    private lateinit var mGalleryUseCase: GalleryUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mGalleryView =
            GalleryView(layoutInflater, null)
        mGalleryUseCase = GalleryUseCase(GallerySDK())

        mGalleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        setContentView(mGalleryView.getRootView())
    }

    override fun onStart() {
        super.onStart()

        mGalleryUseCase.registerListener(this)
        mGalleryView.registerListener(this)

        if (isGalleryCached()) {
            mGalleryView.bindPictures(mGalleryViewModel.mPicturesList)
        } else {
            mGalleryView.showProgressIndicator()
            mGalleryUseCase.fetchGalleryDataAndNotify()
        }
    }

    override fun onStop() {
        super.onStop()

        mGalleryView.unregisterListener(this)
        mGalleryUseCase.unregisterListener(this)
    }

    override fun onPictureClicked(picture: Picture) {
        Toast.makeText(applicationContext, "You clicked ${picture.authorName}", Toast.LENGTH_LONG)
            .show()
    }

    override fun onGalleryDataFetched(pictureList: MutableList<Picture>) {
        cacheGalleryData(pictureList)

        mGalleryView.bindPictures(mGalleryViewModel.mPicturesList)
        mGalleryView.hideProgressIndicator()
    }

    override fun onGalleryFetchFailed() {
        Toast.makeText(applicationContext, "Could not load data", Toast.LENGTH_SHORT).show()
        mGalleryView.hideProgressIndicator()
    }

    private fun cacheGalleryData(pictureList: MutableList<Picture>) {
        mGalleryViewModel.mPicturesList = pictureList
    }

    private fun isGalleryCached(): Boolean {
        return mGalleryViewModel.mPicturesList.size > 0
    }

}
