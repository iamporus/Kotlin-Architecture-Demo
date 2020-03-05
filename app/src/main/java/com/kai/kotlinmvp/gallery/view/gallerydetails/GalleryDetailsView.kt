package com.kai.kotlinmvp.gallery.view.gallerydetails

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kai.kotlinmvp.R
import com.kai.kotlinmvp.base.BaseView
import kotlinx.android.synthetic.main.layout_gallery_details.view.*

class GalleryDetailsView(layoutInflater: LayoutInflater, parent: ViewGroup?) : BaseView() {

    private val view = layoutInflater.inflate(R.layout.layout_gallery_details, parent, false)

    init {
        setRootView(view)
    }

    fun bindPicture(pictureId: Int?) {
        Glide.with(getContext()).load("https://picsum.photos/300/300/?image=${pictureId}")
            .placeholder(R.drawable.default_image).into(view.imageView2)
    }

    fun bindTitle(title: String?) {
        view.pictureTitle.text = title
    }

}