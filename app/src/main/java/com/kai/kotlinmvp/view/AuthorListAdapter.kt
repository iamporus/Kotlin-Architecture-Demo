package com.kai.kotlinmvp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kai.kotlinmvp.R
import com.kai.kotlinmvp.model.Picture
import kotlinx.android.synthetic.main.item_author_list_recycler.view.*


class AuthorListAdapter(private val mPictureList: List<Picture>,
                        private val mListener: OnItemClickListener) : RecyclerView.Adapter< AuthorListAdapter.AuthorViewHolder >()
{
    interface OnItemClickListener{
        fun onItemClicked(pictureItem: Picture)
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): AuthorViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_author_list_recycler, parent, false)
        return AuthorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int)
    {
        holder.bind(mPictureList[position], mListener)
    }

    override fun getItemCount(): Int
    {
        return mPictureList.size
    }

    class AuthorViewHolder( itemView: View ) : RecyclerView.ViewHolder( itemView )
    {
        fun bind(pictureItem: Picture, listener: OnItemClickListener) = with( itemView )
        {
            textView.text = pictureItem.authorName
//            imageView.setImageBitmap( NetworkHelper.getBitMap( "https://picsum.photos/300/300/?image=${authorItem.authorId}" ) )
            Glide.
                with(imageView.context).
                load( "https://picsum.photos/300/300/?image=${pictureItem.authorId}" ).
                placeholder(R.drawable.default_image).
                into( imageView )
            itemView.setOnClickListener{

                listener.onItemClicked(pictureItem) }
        }
    }
}