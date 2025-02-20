package com.example.blogreader.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.blogreader.uimodel.BlogDetailScreen
import com.example.blogreader.uimodel.BlogListScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "blogList") {
        composable("blogList") { BlogListScreen(navController) }
        composable("blogDetail/{blogUrl}") { backStackEntry ->
            val blogUrl = backStackEntry.arguments?.getString("blogUrl") ?: ""
            BlogDetailScreen(blogUrl = Uri.decode(blogUrl))
        }
    }
}
