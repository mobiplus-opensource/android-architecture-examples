package br.com.mobiplus.gitclient.domain.usecases

import br.com.mobiplus.gitclient.domain.base.BaseErrorData
import br.com.mobiplus.gitclient.domain.base.BaseErrorStatus
import br.com.mobiplus.gitclient.domain.base.resultwrapper.ResultWrapper
import br.com.mobiplus.gitclient.domain.base.usecase.BaseAsyncUseCase
import br.com.mobiplus.gitclient.domain.model.PullRequestModel
import br.com.mobiplus.gitclient.domain.repository.PullRequestRepository

class GetPullRequestUseCase(
    private val pullRequestRepository: PullRequestRepository
) : BaseAsyncUseCase<ResultWrapper<PullRequestModel, BaseErrorData<BaseErrorStatus>>, GetPullRequestUseCase.Params>() {

    override suspend fun runAsync(params: Params): ResultWrapper<PullRequestModel, BaseErrorData<BaseErrorStatus>> {
        return pullRequestRepository.getPullRequest(
            owner = params.owner,
            gitRepoName = params.gitRepoName,
            pullRequestNumber = params.pullRequestNumber
        ).transformError { BaseErrorData(errorBody = BaseErrorStatus.DEFAULT_ERROR) }
    }

    data class Params(
        val owner: String,
        val gitRepoName: String,
        val pullRequestNumber: Long
    )
}