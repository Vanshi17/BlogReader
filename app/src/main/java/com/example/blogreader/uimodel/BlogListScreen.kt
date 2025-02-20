package com.example.blogreader.uimodel

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.blogreader.viewmodel.BlogViewModel

@Composable
fun BlogListScreen(navController: NavController, viewModel: BlogViewModel = viewModel()) {
    val blogs by viewModel.blogs.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                if (visibleItems.isNotEmpty() && visibleItems.last().index == blogs.lastIndex) {
                    viewModel.loadNextPage()
                }
            }
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Blog Reader") }) }) { paddingValues ->
        LazyColumn(
            state = listState,
            contentPadding = paddingValues
        ) {
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

            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),

                        )
                }
            }
        }
    }
}
