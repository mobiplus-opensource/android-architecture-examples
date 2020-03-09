package br.com.pebmed.domain.model

import java.util.*

data class PullRequestModel(
    val number: Long,
    val htmlUrl: String,
    val title: String,
    val user: UserModel,
    val body: String,
    val createdAt: Date,
    val comments: Int,
    val commits: Int,
    val additions: Int,
    val deletions: Int,
    val changedFiles: Int
)