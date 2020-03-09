package br.com.pebmed.data.pullRequest

import br.com.pebmed.data.base.BaseDataSourceImpl
import br.com.pebmed.data.pullRequest.model.PullRequestResponseModel
import br.com.pebmed.domain.base.BaseErrorData
import br.com.pebmed.domain.base.FullResultWrapper

class PullRequestRemoteDataSource(
    private val pullRequestApi: PullRequestApi
) : BaseDataSourceImpl() {
    suspend fun getPullRequests(
        owner: String,
        gitRepoName: String
    ): FullResultWrapper<List<PullRequestResponseModel>, BaseErrorData<Unit>> {
        return safeApiCall { pullRequestApi.getPullRequests(owner, gitRepoName) }
    }

    suspend fun getPullRequest(
        owner: String,
        gitRepoName: String,
        pullRequestNumber: Long
    ): FullResultWrapper<PullRequestResponseModel, BaseErrorData<Unit>> {
        return safeApiCall {
            pullRequestApi.getPullRequest(
                owner,
                gitRepoName,
                pullRequestNumber
            )
        }
    }
}