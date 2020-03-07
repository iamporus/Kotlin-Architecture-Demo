package com.kai.picsgallery.gallery.view.grid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kai.picsgallery.R
import com.kai.picsgallery.gallery.model.Picture
import kotlinx.android.synthetic.main.layout_grid_item_picture.view.*

class GalleryGridAdapter(
    private val mListener: OnItemClickListener
) : ListAdapter<Picture, GalleryGridAdapter.GalleryViewHolder>(PictureDiffUtilCallback()) {

    interface OnItemClickListener {
        fun onItemClicked(pictureItem: Picture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_grid_item_picture, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(getItem(position), mListener)
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

    class PictureDiffUtilCallback : DiffUtil.ItemCallback<Picture>() {
        override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
            return oldItem.authorId == newItem.authorId
        }

        override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
            return oldItem == newItem
        }

    }
}