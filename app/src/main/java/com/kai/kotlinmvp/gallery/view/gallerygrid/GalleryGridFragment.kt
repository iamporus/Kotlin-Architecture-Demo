package com.kai.kotlinmvp.gallery.view.gallerygrid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.kai.kotlinmvp.gallery.GalleryUseCase
import com.kai.kotlinmvp.gallery.model.GallerySDK
import com.kai.kotlinmvp.gallery.model.GalleryViewModel
import com.kai.kotlinmvp.gallery.model.Picture

class GalleryGridFragment : Fragment(), GalleryGridView.Listener,
    GalleryUseCase.OnGalleryFetchedListener {

    private lateinit var mGalleryGridView: GalleryGridView
    private lateinit var mGalleryViewModel: GalleryViewModel
    private lateinit var mGalleryUseCase: GalleryUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mGalleryGridView = GalleryGridView(layoutInflater, container)
        mGalleryUseCase = GalleryUseCase(GallerySDK())

        mGalleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        return mGalleryGridView.getRootView()
    }


    override fun onStart() {
        super.onStart()

        mGalleryUseCase.registerListener(this)
        mGalleryGridView.registerListener(this)

        if (isGalleryCached()) {
            mGalleryGridView.bindPictures(mGalleryViewModel.mPicturesList)
        } else {
            mGalleryGridView.showProgressIndicator()
            mGalleryUseCase.fetchGalleryDataAndNotify()
        }
    }

    override fun onStop() {
        super.onStop()

        mGalleryGridView.unregisterListener(this)
        mGalleryUseCase.unregisterListener(this)
    }

    override fun onPictureClicked(picture: Picture) {
        Toast.makeText(context, "You clicked ${picture.authorName}", Toast.LENGTH_LONG)
            .show()
        view?.findNavController()?.navigate(GalleryGridFragmentDirections.gotoGalleryDetails(picture.authorId, picture.authorName))
    }

    override fun onGalleryDataFetched(pictureList: MutableList<Picture>) {
        cacheGalleryData(pictureList)

        mGalleryGridView.bindPictures(mGalleryViewModel.mPicturesList)
        mGalleryGridView.hideProgressIndicator()
    }

    override fun onGalleryFetchFailed() {
        Toast.makeText(context, "Could not load data", Toast.LENGTH_SHORT).show()
        mGalleryGridView.hideProgressIndicator()
    }

    private fun cacheGalleryData(pictureList: MutableList<Picture>) {
        mGalleryViewModel.mPicturesList = pictureList
    }

    private fun isGalleryCached(): Boolean {
        return mGalleryViewModel.mPicturesList.size > 0
    }

}
