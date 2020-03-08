package com.kai.picsgallery.gallery.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kai.picsgallery.R
import com.kai.picsgallery.databinding.FragmentGalleryDetailsBinding
import com.kai.picsgallery.gallery.model.Picture

class GalleryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentGalleryDetailsBinding
    private lateinit var galleryDetailsViewModel: GalleryDetailsViewModel

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

        val args: GalleryDetailsFragmentArgs? = arguments?.let {
            GalleryDetailsFragmentArgs.fromBundle(it)
        }

        val picture = Picture(args!!.pictureId, args.authorName)
        binding.selectedPicture = picture

        binding.lifecycleOwner = this
        galleryDetailsViewModel = ViewModelProvider(
            this,
            GalleryDetailsViewModelFactory(picture)
        ).get(GalleryDetailsViewModel::class.java)
    }

}
