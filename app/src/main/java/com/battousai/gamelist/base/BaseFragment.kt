package com.battousai.gamelist.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment {

    constructor() : super()

    constructor(layoutResId: Int) : super(layoutResId)

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    open fun bind() {}
}