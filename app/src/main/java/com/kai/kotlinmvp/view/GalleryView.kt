package com.kai.kotlinmvp.view

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kai.kotlinmvp.R
import com.kai.kotlinmvp.base.BasePassiveView
import com.kai.kotlinmvp.model.Picture

private const val VERTICAL_SPAN_COUNT = 2
private const val HORIZONTAL_SPAN_COUNT = 5

class GalleryView(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    BasePassiveView<GalleryView.Listener>() {

    interface Listener {
        fun onPictureClicked(picture: Picture)
    }

    private val mProgressBar: ProgressBar
    private val mRecyclerView: RecyclerView

    init {
        setRootView(layoutInflater.inflate(R.layout.layout_gallery, parent, false))

        mProgressBar = findViewById(R.id.progressBar)
        mRecyclerView = findViewById(R.id.recyclerView)

        mRecyclerView.layoutManager =
            GridLayoutManager(getContext(), getSpanCount(), LinearLayoutManager.VERTICAL, false)

        mRecyclerView.setHasFixedSize(true)
    }

    private fun getSpanCount(): Int {
        return if (getRootView().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            VERTICAL_SPAN_COUNT
        else
            HORIZONTAL_SPAN_COUNT
    }

    fun hideProgressIndicator() {
        mProgressBar.visibility = View.GONE
    }

    fun showProgressIndicator() {
        mProgressBar.visibility = View.VISIBLE
    }

    fun bindPictures(pictureList: MutableList<Picture>) {
        mRecyclerView.adapter =
            AuthorListAdapter(pictureList, object : AuthorListAdapter.OnItemClickListener {
                override fun onItemClicked(pictureItem: Picture) {

                    for (listener in getListeners())
                        listener.onPictureClicked(pictureItem)
                }

            })
    }
}
