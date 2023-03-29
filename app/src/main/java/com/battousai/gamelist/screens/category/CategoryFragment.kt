package com.battousai.gamelist.screens.category

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.battousai.gamelist.R
import com.battousai.gamelist.base.BaseFragment
import com.battousai.gamelist.base.viewBinding
import com.battousai.gamelist.databinding.FragmentCategoryBinding
import com.battousai.gamelist.managers.NavigationManager
import com.battousai.gamelist.models.CategoryListResponseModel
import com.battousai.gamelist.screens.category.list.CategoryListAdapter


class CategoryFragment : BaseFragment(R.layout.fragment_category) {
    private val viewBinding by viewBinding(FragmentCategoryBinding::bind)
    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var categoryListAdapter: CategoryListAdapter

    override fun bind() {
        super.bind()
        viewModel.apply {
            eventFetchData.observe(viewLifecycleOwner, handleFetchData)
            eventShowProgress.observe(viewLifecycleOwner, handleShowProgress)
            eventItemClick.observe(viewLifecycleOwner, handleItemClick)
            getData()
        }
    }

    private val handleFetchData = Observer<CategoryListResponseModel> {
        categoryListAdapter = CategoryListAdapter(it.results, viewModel)
        viewBinding.rvCategory.adapter = categoryListAdapter
    }

    private val handleShowProgress = Observer<Boolean> {
        viewBinding.progress.isVisible = it
    }

    private val handleItemClick = Observer<Int> {
        NavigationManager.shared.present(this, R.id.categoryDetailFragment, it)
    }

}