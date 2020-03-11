package br.com.mobiplus.gitclient.data.pullRequest

import br.com.mobiplus.gitclient.data.pullRequest.model.PullRequestResponseModel
import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.FullResultWrapper
import br.com.mobiplus.gitclient.domain.model.GithubError
import br.com.mobiplus.gitclient.domain.model.PullRequestModel
import br.com.mobiplus.gitclient.domain.repository.PullRequestRepository

class PullRequestRepositoryImpl(
    private val pullRequestRemoteDataSource: PullRequestRemoteDataSource
) : PullRequestRepository {
    override suspend fun getPullRequestList(
        owner: String,
        gitRepoName: String
    ): FullResultWrapper<List<PullRequestModel>, BaseErrorData<GithubError>> {
        val listPullRequests = pullRequestRemoteDataSource.getPullRequestList(owner, gitRepoName)

        return listPullRequests.transformSuccess(handleGetPullRequestsSuccess())
    }

    override suspend fun getPullRequest(
        owner: String,
        gitRepoName: String,
        pullRequestNumber: Long
    ): FullResultWrapper<PullRequestModel, BaseErrorData<GithubError>> {
        val pullRequest =
            pullRequestRemoteDataSource.getPullRequest(owner, gitRepoName, pullRequestNumber)

        return pullRequest.transformSuccess { it.mapTo() }
    }

    private fun handleGetPullRequestsSuccess(): (List<PullRequestResponseModel>) -> List<PullRequestModel> {
        return {
            it.map { pullRequestResponse ->
                pullRequestResponse.mapTo()
            }
        }
    }
}