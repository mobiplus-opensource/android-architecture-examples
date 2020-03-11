package br.com.mobiplus.gitclient.domain.repository

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import br.com.mobiplus.gitclient.domain.model.GithubError
import br.com.mobiplus.gitclient.domain.model.PullRequestModel

interface PullRequestRepository {
    suspend fun getPullRequestList(
        owner: String,
        gitRepoName: String
    ): ResultWrapper<List<PullRequestModel>, BaseErrorData<GithubError>>

    suspend fun getPullRequest(
        owner: String,
        gitRepoName: String,
        pullRequestNumber: Long
    ): ResultWrapper<PullRequestModel, BaseErrorData<GithubError>>
}