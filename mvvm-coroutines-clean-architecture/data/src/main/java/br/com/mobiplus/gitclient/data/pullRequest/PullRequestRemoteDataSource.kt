package br.com.mobiplus.gitclient.data.pullRequest

import br.com.mobiplus.gitclient.data.base.BaseDataSourceImpl
import br.com.mobiplus.gitclient.data.pullRequest.model.PullRequestResponseModel
import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.FullResultWrapper

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