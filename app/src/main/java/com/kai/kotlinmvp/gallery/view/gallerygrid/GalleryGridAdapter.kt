package com.kai.kotlinmvp.gallery.view.gallerygrid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kai.kotlinmvp.R
import com.kai.kotlinmvp.gallery.model.Picture
import kotlinx.android.synthetic.main.layout_grid_item_picture.view.*

class GalleryGridAdapter(
    private val mPictureList: List<Picture>,
    private val mListener: OnItemClickListener
) : RecyclerView.Adapter<GalleryGridAdapter.GalleryViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(pictureItem: Picture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_grid_item_picture, parent, false)
        return GalleryViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(mPictureList[position], mListener)
    }

    override fun getItemCount(): Int {
        return mPictureList.size
    }

    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pictureItem: Picture, listener: OnItemClickListener) = with(itemView)
        {
            textView.text = pictureItem.authorName
            Glide.with(imageView.context)
                .load("https://picsum.photos/300/300/?image=${pictureItem.authorId}")
                .placeholder(R.drawable.default_image).into(imageView)
            itemView.setOnClickListener {

                listener.onItemClicked(pictureItem)
            }
        }
    }
}