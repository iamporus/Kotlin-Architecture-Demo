package com.kai.picsgallery.gallery.view.grid

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kai.picsgallery.R

@BindingAdapter("authorName")
fun TextView.setPictureAuthor(authorName: String) {
    text = authorName
}

@BindingAdapter("authorId")
fun ImageView.setPictureId(authorId: Int) {
    Glide.with(context)
        .load("https://picsum.photos/300/300/?image=${authorId}")
        .placeholder(R.drawable.default_image).into(this)
}