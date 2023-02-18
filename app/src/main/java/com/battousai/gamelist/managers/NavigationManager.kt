package com.battousai.gamelist.managers

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.battousai.gamelist.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class NavigationManager {

    private val extraParcelable = "EXTRA_PARCELABLE"
    private val extraAny = "EXTRA_ANY"

    private val listTab = listOf(
        R.id.home_fragment,
        R.id.favorite_fragment,
        R.id.help_fragment
    )

    private fun setUpGraph(navController: NavController) {
        val graphInflater = navController.navInflater
        val graph = graphInflater.inflate(R.navigation.nav_main)
        graph.setStartDestination(R.id.home_fragment)
        val args = bundleOf(extraAny to null)
        navController.setGraph(graph, args)
    }

    private fun setUpTabs(navController: NavController, activity: FragmentActivity) {
        getTabBar(activity).apply {
            setupWithNavController(navController)
            setOnItemSelectedListener(handleItemListener(navController))
            setOnItemReselectedListener { }
        }
        navController.addOnDestinationChangedListener(handleDestinationChangeListener(activity))
    }

    fun launch(activity: FragmentActivity) {
        val navController = getNavController(activity)
        setUpGraph(navController)
        setUpTabs(navController, activity)
    }

    fun dismiss(fragment: Fragment) {
        getNavController(fragment.requireActivity()).navigateUp()
    }

    fun params(fragment: Fragment): Any? {
        var extraP: Any? = fragment.arguments?.getParcelable(extraParcelable)
        extraP?.let { safeP -> return safeP }
        extraP = fragment.arguments?.get(extraAny)
        extraP?.let { safeA -> return safeA }
        return null
    }

    fun present(source: Fragment, @IdRes fragment: Int, params: Any? = null) {
        val bundle = when (params) {
            is Parcelable -> bundleOf(extraParcelable to params)
            else -> bundleOf(extraAny to params)
        }
        show(source, fragment, bundle)
    }

    private fun show(fragment: Fragment, @IdRes destination: Int, bundle: Bundle? = null) {
        getNavController(fragment.requireActivity()).navigate(destination, bundle)
    }

    private fun launchSequence(
        activity: FragmentActivity
    ) {
        getTabBar(activity).apply {
            menu.clear()
            inflateMenu(R.menu.menu_nav_bottom)
        }
        val navController = getNavController(activity)
        val graph = navController.graph
        graph.setStartDestination(R.id.home_fragment)
        navController.graph = graph
    }

    fun dismiss(from: Fragment, @IdRes to: Int?, model: Any? = null) {
        val navController = getNavController(from.requireActivity())
        to?.let {
            try {
                val backStackEntry = navController.getBackStackEntry(to)
                val arguments = backStackEntry.arguments
                val params = model
                    ?: arguments?.getParcelable(extraParcelable)
                    ?: arguments?.get(extraAny)

                navController.popBackStack(to, true)
                present(from, to, params)
            } catch (e: IllegalArgumentException) {
                if (to in listTab) popRoot(navController) else popTab(navController)
                present(from, to, model)
            }
        } ?: popTab(navController)
    }


    fun dismissDialog(activity: FragmentActivity, @IdRes destination: Int) {
        val navController = getNavController(activity)
        navController.popBackStack(destination, false)
    }

    private fun popTab(navController: NavController) {
        navController.backQueue.map { it.destination.id }.lastOrNull { it in listTab }?.let {
            navController.popBackStack(it, false)
        } ?: popRoot(navController)
    }

    private fun popRoot(navController: NavController) {
        val startDestination = navController.graph.startDestinationId
        navController.popBackStack(startDestination, false)
    }

    private fun getNavController(activity: FragmentActivity): NavController {
        val navHostFragment = activity
            .supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }

    private fun handleItemListener(navController: NavController) =
        NavigationBarView.OnItemSelectedListener { menuItem ->
            NavigationUI.onNavDestinationSelected(menuItem, navController)
        }

    private fun handleDestinationChangeListener(activity: FragmentActivity) =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            getTabBar(activity).isVisible = listTab.contains(destination.id)
        }

    fun currentFragment(activity: FragmentActivity): Int? {
        return getNavController(activity).currentDestination?.id
    }

    fun previousFragment(activity: FragmentActivity): Int? {
        return getNavController(activity).previousBackStackEntry?.destination?.id
    }

    private fun getTabBar(activity: FragmentActivity): BottomNavigationView {
        return activity.findViewById(R.id.tabBar)
    }

    companion object {

        val shared: NavigationManager = getInstance()

        @Volatile
        private var instance: NavigationManager? = null

        private fun init(): NavigationManager = instance ?: synchronized(this) {
            instance ?: NavigationManager().also {
                instance = it
            }
        }

        private fun getInstance(): NavigationManager =
            instance ?: init()
    }


}