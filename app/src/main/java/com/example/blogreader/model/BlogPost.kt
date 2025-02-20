package com.example.blogreader.model

data class BlogPost(
    val id: Int,
    val title: Title,
    val link: String
) {
    data class Title(val rendered: String)
}
