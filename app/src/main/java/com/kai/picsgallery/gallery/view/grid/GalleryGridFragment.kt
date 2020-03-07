package com.kai.picsgallery.gallery.view.grid

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kai.picsgallery.R
import com.kai.picsgallery.databinding.FragmentGalleryGridBinding
import com.kai.picsgallery.gallery.GalleryViewModel
import com.kai.picsgallery.gallery.model.Picture

private const val VERTICAL_SPAN_COUNT = 2
private const val HORIZONTAL_SPAN_COUNT = 5

class GalleryGridFragment : Fragment(), GalleryGridAdapter.OnItemClickListener {

    private lateinit var mGalleryViewModel: GalleryViewModel
    private lateinit var mBinding: FragmentGalleryGridBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_gallery_grid,
            container, false
        )

        mBinding.lifecycleOwner = this

        mGalleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun initRecyclerView() {
        mBinding.recyclerView.layoutManager = GridLayoutManager(
            context,
            getSpanCount(),
            LinearLayoutManager.VERTICAL, false
        )

        mBinding.recyclerView.setHasFixedSize(true)

        val adapter = GalleryGridAdapter(this)

        mBinding.recyclerView.adapter = adapter

        mGalleryViewModel.bIsFetching.observe(viewLifecycleOwner) { fetchStatus ->
            if (fetchStatus)
                showProgressIndicator()
            else
                hideProgressIndicator()
        }

        mGalleryViewModel.mPicturesList.observe(viewLifecycleOwner) { pictureList ->
            adapter.submitList(pictureList)
        }

        mGalleryViewModel.bFetchFailed.observe(viewLifecycleOwner) { fetchFailed ->
            if (fetchFailed)
                Toast.makeText(context, "Error while fetching pictures!", Toast.LENGTH_SHORT).show()
        }

        mGalleryViewModel.bNavigateToDetailsEvent.observe(viewLifecycleOwner) { picture: Picture? ->

            picture?.let {
                this.findNavController().navigate(
                    GalleryGridFragmentDirections.gotoGalleryDetails(
                        picture.authorId,
                        picture.authorName
                    )
                )
                mGalleryViewModel.onNavigatedToPictureDetails()
            }
        }

    }

    private fun getSpanCount(): Int {
        return if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            VERTICAL_SPAN_COUNT
        else
            HORIZONTAL_SPAN_COUNT
    }

    private fun hideProgressIndicator() {
        mBinding.progressBar.visibility = View.GONE
    }

    private fun showProgressIndicator() {
        mBinding.progressBar.visibility = View.VISIBLE
    }

    override fun onItemClicked(pictureItem: Picture) {
        mGalleryViewModel.onPictureClicked(pictureItem)
    }

}
