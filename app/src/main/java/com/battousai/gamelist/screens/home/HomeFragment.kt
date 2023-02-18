package com.battousai.gamelist.screens.home

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.battousai.gamelist.R
import com.battousai.gamelist.base.BaseFragment
import com.battousai.gamelist.base.viewBinding
import com.battousai.gamelist.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewBinding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    override fun bind() {
        super.bind()

        viewBinding.apply {
            buttonFetchData.setOnClickListener {
                viewModel.getData()
            }
        }


        viewModel.apply {
            eventFetchData.observe(viewLifecycleOwner, handleFetchData)
            eventShowProgress.observe(viewLifecycleOwner, handleShowProgress)
        }
    }


    private val handleFetchData = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }

    private val handleShowProgress = Observer<Boolean> {
        viewBinding.progress.isVisible = it
    }
}