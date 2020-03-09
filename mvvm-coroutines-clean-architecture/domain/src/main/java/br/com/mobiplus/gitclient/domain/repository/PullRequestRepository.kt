package br.com.mobiplus.gitclient.domain.repository

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import br.com.mobiplus.gitclient.domain.model.PullRequestModel

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