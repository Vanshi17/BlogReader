package com.example.blogreader.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blogreader.api.RetrofitInstance
import com.example.blogreader.model.BlogPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BlogViewModel : ViewModel() {
    private val _blogs = MutableStateFlow<List<BlogPost>>(emptyList())
    val blogs: StateFlow<List<BlogPost>> = _blogs

    private var currentPage = 1
    private val perPage = 10

    init {
        fetchBlogs()
    }

    fun fetchBlogs() {
        viewModelScope.launch {
            try {
                val newBlogs = RetrofitInstance.api.getBlogPosts(perPage, currentPage)
                _blogs.value = _blogs.value + newBlogs
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadNextPage() {
        currentPage++
        fetchBlogs()
    }
}
