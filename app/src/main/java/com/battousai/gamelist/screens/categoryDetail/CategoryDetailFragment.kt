package com.battousai.gamelist.screens.categoryDetail

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.battousai.gamelist.R
import com.battousai.gamelist.base.BaseFragment
import com.battousai.gamelist.base.viewBinding
import com.battousai.gamelist.databinding.FragmentCategoryDetailBinding
import com.battousai.gamelist.managers.NavigationManager
import com.battousai.gamelist.models.CategoryDetailResponseModel
import com.bumptech.glide.Glide

class CategoryDetailFragment : BaseFragment(R.layout.fragment_category_detail){
    private val viewBinding by viewBinding(FragmentCategoryDetailBinding::bind)
    private val viewModel: CategoryDetailViewModel by viewModels()


    override fun bind() {
        super.bind()
        val categoryId = NavigationManager.shared.params(this) as Int

        viewModel.apply {
            eventFetchData.observe(viewLifecycleOwner, handleFetchData)
            eventShowProgress.observe(viewLifecycleOwner, handleShowProgress)
            getData(categoryId)
        }
    }

    @SuppressLint("SetTextI18n")
    private val handleFetchData = Observer<CategoryDetailResponseModel> { model->
        viewBinding.apply {
            tvCategoryCount.text="${model.games_count} Games"
            tvCategoryName.text=model.name
            tvCategoryDetail.text=model.description.substring(3,model.description.length-4)
            Glide
                .with(this@CategoryDetailFragment)
                .load(model.image_background)
                .centerCrop()
                .into(imageCategory)
        }

    }

    private val handleShowProgress = Observer<Boolean> {
        viewBinding.progress.isVisible = it
    }

}