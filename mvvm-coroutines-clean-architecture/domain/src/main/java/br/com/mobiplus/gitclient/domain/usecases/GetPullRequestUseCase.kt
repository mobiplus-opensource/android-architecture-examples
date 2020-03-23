package br.com.mobiplus.gitclient.domain.usecases

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import br.com.mobiplus.gitclient.domain.base.usecase.BaseAsyncUseCase
import br.com.mobiplus.gitclient.domain.model.GithubError
import br.com.mobiplus.gitclient.domain.model.PullRequestModel
import br.com.mobiplus.gitclient.domain.repository.PullRequestRepository

class GetPullRequestUseCase(
    private val pullRequestRepository: PullRequestRepository
) : BaseAsyncUseCase<ResultWrapper<PullRequestModel, BaseErrorData<GithubError>>, GetPullRequestUseCase.Params>() {

    override suspend fun runAsync(params: Params): ResultWrapper<PullRequestModel, BaseErrorData<GithubError>> {
        return pullRequestRepository.getPullRequest(
            owner = params.owner,
            gitRepoName = params.gitRepoName,
            pullRequestNumber = params.pullRequestNumber
        )
    }

    data class Params(
        val owner: String,
        val gitRepoName: String,
        val pullRequestNumber: Long
    )
}