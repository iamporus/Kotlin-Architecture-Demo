package com.kai.kotlinmvp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.kai.kotlinmvp.model.Picture
import com.kai.kotlinmvp.presenter.GalleryPresenter

class MainActivity : AppCompatActivity(), GalleryView.Listener,
    GalleryPresenter.OnGalleryFetchedListener {

    private lateinit var mGalleryView: GalleryView
    private lateinit var mGalleryViewModel: GalleryViewModel
    private lateinit var mGalleryPresenter: GalleryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mGalleryView = GalleryView(layoutInflater, null)
        mGalleryPresenter = GalleryPresenter()

        mGalleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)

        setContentView(mGalleryView.getRootView())
    }

    override fun onStart() {
        super.onStart()

        mGalleryPresenter.registerListener(this)
        mGalleryView.registerListener(this)

        if (isGalleryCached()) {
            mGalleryView.bindPictures(mGalleryViewModel.mPicturesList)
        } else {
            mGalleryView.showProgressIndicator()
            mGalleryPresenter.fetchGalleryDataAndNotify()
        }
    }

    override fun onStop() {
        super.onStop()

        mGalleryView.unregisterListener(this)
        mGalleryPresenter.unregisterListener(this)
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
