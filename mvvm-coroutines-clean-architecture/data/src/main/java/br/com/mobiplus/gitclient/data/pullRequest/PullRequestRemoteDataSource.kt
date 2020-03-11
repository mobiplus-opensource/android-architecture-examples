package br.com.mobiplus.gitclient.data.pullRequest

import br.com.mobiplus.gitclient.data.base.BaseDataSourceImpl
import br.com.mobiplus.gitclient.data.pullRequest.model.PullRequestResponseModel
import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.FullResultWrapper
import br.com.mobiplus.gitclient.domain.model.GithubError

class PullRequestRemoteDataSource(
    private val pullRequestApi: PullRequestApi
) : BaseDataSourceImpl() {
    suspend fun getPullRequestList(
        owner: String,
        gitRepoName: String
    ): FullResultWrapper<List<PullRequestResponseModel>, BaseErrorData<GithubError>> {
        return safeApiCall { pullRequestApi.getPullRequestList(owner, gitRepoName) }
    }

    suspend fun getPullRequest(
        owner: String,
        gitRepoName: String,
        pullRequestNumber: Long
    ): FullResultWrapper<PullRequestResponseModel, BaseErrorData<GithubError>> {
        return safeApiCall {
            pullRequestApi.getPullRequest(
                owner,
                gitRepoName,
                pullRequestNumber
            )
        }
    }
}