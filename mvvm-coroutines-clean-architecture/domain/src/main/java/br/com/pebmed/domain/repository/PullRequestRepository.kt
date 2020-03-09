package br.com.pebmed.domain.repository

import br.com.pebmed.domain.base.BaseErrorData
import br.com.pebmed.domain.base.ResultWrapper
import br.com.pebmed.domain.model.PullRequestModel

interface PullRequestRepository {
    suspend fun getPullRequests(
        owner: String,
        gitRepoName: String
    ): ResultWrapper<List<PullRequestModel>, BaseErrorData<Unit>>

    suspend fun getPullRequest(
        owner: String,
        gitRepoName: String,
        pullRequestNumber: Long
    ): ResultWrapper<PullRequestModel, BaseErrorData<Unit>>
}