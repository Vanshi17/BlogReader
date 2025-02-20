package com.example.blogreader.uimodel

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.blogreader.viewmodel.BlogViewModel
import androidx.compose.ui.Modifier


@Composable
fun BlogListScreen(navController: NavController, viewModel: BlogViewModel = viewModel()) {
    val blogs by viewModel.blogs.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Blog Reader") }) }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) { // Fix padding issue
            items(blogs) { blog ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("blogDetail/${Uri.encode(blog.link)}")
                        },
                    elevation = 4.dp
                ) {
                    Text(
                        text = blog.title.rendered,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}