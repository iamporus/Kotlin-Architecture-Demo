package com.kai.picsgallery.gallery.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.kai.picsgallery.R
import com.kai.picsgallery.databinding.FragmentGalleryDetailsBinding

class GalleryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentGalleryDetailsBinding
    private var mPictureAuthor: String? = ""
    private var mPictureId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args: GalleryDetailsFragmentArgs? = arguments?.let {
            GalleryDetailsFragmentArgs.fromBundle(it)
        }
        mPictureId = args?.pictureId
        mPictureAuthor = args?.authorName

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_gallery_details, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            Glide.with(it).load("https://picsum.photos/300/300/?image=${mPictureId}")
                .placeholder(R.drawable.default_image).into(binding.imageView2)
        }

        binding.pictureTitle.text = mPictureAuthor
    }

}
