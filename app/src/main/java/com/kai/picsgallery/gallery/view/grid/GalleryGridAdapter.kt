package com.kai.picsgallery.gallery.view.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kai.picsgallery.R
import com.kai.picsgallery.databinding.LayoutGridItemPictureBinding
import com.kai.picsgallery.gallery.model.Picture

class GalleryGridAdapter(
    private val mListener: OnItemClickListener
) : ListAdapter<Picture, GalleryGridAdapter.GalleryViewHolder>(PictureDiffUtilCallback()) {


    interface OnItemClickListener {
        fun onItemClicked(pictureItem: Picture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(getItem(position), mListener)
    }

    class GalleryViewHolder(private val binding: LayoutGridItemPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pictureItem: Picture, listener: OnItemClickListener) = with(itemView)
        {
            binding.picture = pictureItem
            binding.onItemClickListener = listener
            binding.executePendingBindings()
        }

        companion object {

            fun inflate(parent: ViewGroup): GalleryViewHolder {

                val binding: LayoutGridItemPictureBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_grid_item_picture,
                    parent,
                    false
                );
                return GalleryViewHolder(binding)
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