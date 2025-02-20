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

    init {
        fetchBlogs()
    }

    private fun fetchBlogs() {
        viewModelScope.launch {
            try {
                _blogs.value = RetrofitInstance.api.getBlogPosts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
