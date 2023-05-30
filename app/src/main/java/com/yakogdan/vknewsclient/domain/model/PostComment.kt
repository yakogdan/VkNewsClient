package com.yakogdan.vknewsclient.domain.model

import com.yakogdan.vknewsclient.R

data class PostComment(
    val id: Int,
    val authorName: String = "Autor",
    val authorAvatarId: Int = R.drawable.comment_author_avatar,
    val commentText: String = "Long comment text",
    val publicationDate: String = "14:00"
)
