package com.vasyancoder.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import java.io.Serializable

private const val NAVIGATION_DATA = "navigation data"

fun Fragment.navigate(
    actionId: Int,
    hostId: Int? = null,
    data: Serializable? = null,
    navOptions: NavOptions? = null,
    extras: Navigator.Extras? = null
) {
    val navController = if (hostId == null) {
        findNavController()
    } else {
        Navigation.findNavController(requireActivity(), hostId)
    }

    val bundle = Bundle().apply { putSerializable(NAVIGATION_DATA, data) }

    navController.navigate(
        resId = actionId,
        args = bundle,
        navOptions = navOptions,
        navigatorExtras = extras
    )
}

val Fragment.navigationData: Serializable?
    get() = arguments?.getSerializable(NAVIGATION_DATA)