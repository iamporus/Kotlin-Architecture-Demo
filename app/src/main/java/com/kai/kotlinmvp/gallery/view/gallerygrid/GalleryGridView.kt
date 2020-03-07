package com.kai.kotlinmvp.gallery.view.gallerygrid

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kai.kotlinmvp.R
import com.kai.kotlinmvp.base.BasePassiveView
import com.kai.kotlinmvp.gallery.model.Picture
import kotlinx.android.synthetic.main.layout_gallery.view.recyclerView
import kotlinx.android.synthetic.main.layout_gallery.view.progressBar

private const val VERTICAL_SPAN_COUNT = 2
private const val HORIZONTAL_SPAN_COUNT = 5

class GalleryGridView(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BasePassiveView<GalleryGridView.Listener>() {

    interface Listener {
        fun onPictureClicked(picture: Picture)
    }

    private val view = layoutInflater.inflate(R.layout.layout_gallery, parent, false)

    init {

        setRootView(view)

        view.recyclerView.layoutManager = GridLayoutManager(
            getContext(),
            getSpanCount(),
            LinearLayoutManager.VERTICAL, false)

        view.recyclerView.setHasFixedSize(true)
    }

    private fun getSpanCount(): Int {
        return if (getRootView().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            VERTICAL_SPAN_COUNT
        else
            HORIZONTAL_SPAN_COUNT
    }

    fun hideProgressIndicator() {
        view.progressBar.visibility = View.GONE
    }

    fun showProgressIndicator() {
        view.progressBar.visibility = View.VISIBLE
    }

    fun bindPictures(pictureList: MutableList<Picture>) {
        view.recyclerView.adapter =
            GalleryGridAdapter(
                pictureList,
                object :
                    GalleryGridAdapter.OnItemClickListener {
                    override fun onItemClicked(pictureItem: Picture) {

                        for (listener in mListeners)
                            listener.onPictureClicked(pictureItem)
                    }

                })
    }
}
