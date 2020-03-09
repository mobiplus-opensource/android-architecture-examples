package br.com.pebmed.data.pullRequest

import br.com.pebmed.data.pullRequest.model.PullRequestResponseModel
import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.FullResultWrapper
import br.com.mobiplus.gitclient.domain.model.PullRequestModel
import br.com.mobiplus.gitclient.domain.repository.PullRequestRepository

class PullRequestRepositoryImpl(
    private val pullRequestRemoteDataSource: PullRequestRemoteDataSource
) : PullRequestRepository {
    override suspend fun getPullRequests(
        owner: String,
        gitRepoName: String
    ): FullResultWrapper<List<PullRequestModel>, BaseErrorData<Unit>> {
        val listPullRequests = pullRequestRemoteDataSource.getPullRequests(owner, gitRepoName)

        return listPullRequests.transformSuccess(handleGetPullRequestsSuccess())
    }

    override suspend fun getPullRequest(
        owner: String,
        gitRepoName: String,
        pullRequestNumber: Long
    ): FullResultWrapper<PullRequestModel, BaseErrorData<Unit>> {
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