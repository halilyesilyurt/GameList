package com.battousai.gamelist.screens.favorite

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.battousai.gamelist.R
import com.battousai.gamelist.base.BaseFragment
import com.battousai.gamelist.base.viewBinding
import com.battousai.gamelist.databinding.FragmentFavoriteBinding
import com.battousai.gamelist.models.GameModel
import com.battousai.gamelist.screens.home.HomeViewModel
import androidx.lifecycle.Observer

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {
    private val viewBinding by viewBinding(FragmentFavoriteBinding::bind)
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var favoriteListAdapter: FavoriteListAdapter

    override fun bind() {
        super.bind()
        viewBinding.apply {
            favoriteListAdapter = FavoriteListAdapter()
            viewBinding.rvFavorites.adapter = favoriteListAdapter
        }

        viewModel.apply {
            eventFetchData.observe(viewLifecycleOwner, handleFetchData)
            eventShowProgress.observe(viewLifecycleOwner, handleShowProgress)
            getData()
        }
    }


    private val handleFetchData = Observer<List<GameModel>?> {
      favoriteListAdapter.updateList(it)
    }


    private val handleShowProgress = Observer<Boolean> {
        viewBinding.progress.isVisible = it
    }


}