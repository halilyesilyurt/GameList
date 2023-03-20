package com.battousai.gamelist.screens.home

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.battousai.gamelist.R
import com.battousai.gamelist.base.BaseFragment
import com.battousai.gamelist.base.viewBinding
import com.battousai.gamelist.databinding.FragmentHomeBinding
import com.battousai.gamelist.models.GameListResponseModel
import com.battousai.gamelist.models.GameModel
import com.battousai.gamelist.screens.home.list.GameListAdapter

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewBinding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var gameListAdapter: GameListAdapter

    override fun bind() {
        super.bind()
        viewModel.apply {
            eventFetchData.observe(viewLifecycleOwner, handleFetchData)
            eventShowProgress.observe(viewLifecycleOwner, handleShowProgress)

            getData()
        }
    }


    private val handleFetchData = Observer<GameListResponseModel> {
        gameListAdapter = GameListAdapter(it.results)
        viewBinding.rvGames.adapter = gameListAdapter
    }

    private fun GameListAdapter(gameList: List<GameModel>): GameListAdapter {
            TODO()
    }


    private val handleShowProgress = Observer<Boolean> {
        viewBinding.progress.isVisible = it
    }
}