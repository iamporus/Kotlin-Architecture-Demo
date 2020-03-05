package com.kai.kotlinmvp.gallery.view.gallerydetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kai.kotlinmvp.R

class GalleryDetailsFragment : Fragment() {

    private lateinit var mGalleryDetailsView: GalleryDetailsView
    private var mPictureAuthor: String? = ""
    private var mPictureId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args: GalleryDetailsFragmentArgs? = arguments?.let { GalleryDetailsFragmentArgs.fromBundle(it) }
        mPictureId = args?.pictureId
        mPictureAuthor = args?.authorName

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mGalleryDetailsView = GalleryDetailsView(inflater, container)
        return mGalleryDetailsView.getRootView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mGalleryDetailsView.bindPicture(mPictureId)
        mGalleryDetailsView.bindTitle(mPictureAuthor)
    }

}
